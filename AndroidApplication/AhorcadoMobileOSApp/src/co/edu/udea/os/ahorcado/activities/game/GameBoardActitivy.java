package co.edu.udea.os.ahorcado.activities.game;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.categories.CategoriesDashboardActivity;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.categorywords.CategoryWordsAsyncTask;

public class GameBoardActitivy extends Activity {

	private static final String TAG = GameBoardActitivy.class.getSimpleName();

	private static final char MASK = '-';

	private Chronometer chronometer;
	private TextView recordTextView;
	private TextView wordTextView;

	private int currectRecord;
	private int punishmentRecord;

	private Category category;
	private CategoryWords categoryWords;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_game_board);

		this.extractBundleExtra(super.getIntent());
		this.findWordForCategory();
		this.createViewComponents();
	}

	@Override()
	public void onBackPressed() {
		AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
				this)).createAlertDialog(
				super.getResources().getString(
						R.string.leave_game_title_alert_dialog),
				super.getResources().getString(
						R.string.leave_game_text_alert_dialog), false);
		alertDialogBuilder.setPositiveButton(
				super.getResources().getString(R.string.accept),
				new DialogInterface.OnClickListener() {

					@Override()
					public void onClick(DialogInterface dialog, int id) {
						Log.d(GameBoardActitivy.TAG, "Finishing Activity: "
								+ GameBoardActitivy.class.getSimpleName());

						GameBoardActitivy.this.chronometer.stop();
						GameBoardActitivy.this.finish();
					}
				});
		alertDialogBuilder.setNegativeButton(
				super.getResources().getString(R.string.cancel),
				new DialogInterface.OnClickListener() {

					@Override()
					public void onClick(DialogInterface dialog, int id) {
						GameBoardActitivy.this.chronometer.start();
					}
				});
		(alertDialogBuilder.create()).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		this.chronometer.stop();
	}

	@Override()
	protected void onResume() {
		super.onResume();

		this.chronometer.start();
	}

	public void onGuessLetter(View view) {
		Button button = (Button) view;

		Log.d(TAG, "Button Letter Pressed: " + button.getText().toString());

		this.revealLetter(button.getText().charAt(0));

		button.setEnabled(false);
	}

	private void createViewComponents() {
		super.setTitle(this.category.getName());

		this.chronometer = (Chronometer) super.findViewById(R.id.chronometer);
		this.chronometer.setBase(SystemClock.elapsedRealtime());

		this.recordTextView = (TextView) super
				.findViewById(R.id.score_text_view);

		this.currectRecord = super.getResources().getInteger(
				R.integer.initial_record_points);

		this.punishmentRecord = super.getResources().getInteger(
				R.integer.punishment_points);

		this.wordTextView = (TextView) super
				.findViewById(R.id.hidden_word_text_view);
		this.wordTextView.setText(this.maskWord());
	}

	private void extractBundleExtra(Intent intent) {
		Log.d(GameBoardActitivy.TAG, "Extracting Intent's Data.");

		Bundle bundle = intent.getExtras();

		this.category = bundle
				.getParcelable(CategoriesDashboardActivity.CATEGORY_SELECTED);
		this.webServiceServer = bundle
				.getParcelable(CategoriesDashboardActivity.WEB_SERVER_CONFIG);
	}

	private void findWordForCategory() {
		ProgressDialog progressDialog = (new ProgressBarCustomized(this))
				.createProgressDialog(
						super.getResources()
								.getString(
										R.string.find_category_words_title_progress_dialog),
						super.getResources()
								.getString(
										R.string.find_category_words_text_progress_dialog),
						false);

		CategoryWordsAsyncTask categoryWordsAsyncTask = new CategoryWordsAsyncTask(
				this.webServiceServer, progressDialog,
				CategoryWordsAsyncTask.ONE_WORD_FOR_CATEGORY);

		categoryWordsAsyncTask.execute(this.category.getName());
		try {
			List<CategoryWords> categoriesWords = categoryWordsAsyncTask.get();

			if ((categoriesWords == null) || (categoriesWords.isEmpty())) {
				Log.e(TAG, "Categories Words Not Found.");

				AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
						this))
						.createAlertDialog(
								super.getResources()
										.getString(
												R.string.category_words_error_title_alert_dialog),
								super.getResources()
										.getString(
												R.string.category_words_error_text_alert_dialog),
								true);
				alertDialogBuilder.setPositiveButton(super.getResources()
						.getString(R.string.accept), new OnClickListener() {

					@Override()
					public void onClick(DialogInterface dialog, int which) {
						Log.e(GameBoardActitivy.TAG,
								"No Categories Words Found.");

						GameBoardActitivy.this.finish();
					}
				});
				(alertDialogBuilder.create()).show();
			} else {
				this.categoryWords = categoriesWords.get(0);

				Log.d(GameBoardActitivy.TAG, "Word Selected: "
						+ this.categoryWords.getWord1().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private String maskWord() {
		StringBuilder string = new StringBuilder(this.categoryWords.getWord1()
				.getName());

		for (int i = 0; i < string.length(); i++) {
			string.setCharAt(i, GameBoardActitivy.MASK);
		}

		return (string.toString());
	}

	private void revealLetter(char letter) {
		StringBuilder string = new StringBuilder(this.wordTextView.getText()
				.toString());
		String word = this.categoryWords.getWord1().getName();

		boolean gotIn = false;
		int index = word.indexOf(letter, 0);
		while (index != -1) {
			string.setCharAt(index, letter);
			index = word.indexOf(letter, index + 1);
			gotIn = true;
		}

		if (gotIn == false) {
			this.currectRecord -= this.punishmentRecord;

			this.recordTextView.setText(Integer.toString(this.currectRecord));
		} else {
			this.wordTextView.setText(string);
		}
	}
}