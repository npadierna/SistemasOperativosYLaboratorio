package co.edu.udea.os.ahorcado.activities.game;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.categories.CategoriesDashboardActivity;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.categorywords.CategoryWordsAsyncTask;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class GameBoardActivity extends Activity {

	private static final String TAG = GameBoardActivity.class.getSimpleName();

	public static final String CURRENT_HANG_GAME = "Current Hang Game";
	public static final String WEB_SERVER_CONFIG = "Web Server Configuration";

	public static final String DATE_FORMAT_PATTERN = "dd/mm/yyyy hh:mm:ss a";
	private static final String MASK = "-";

	public static final String CATEGORY = "Category Name";
	public static final String CATEGORY_WORDS = "Word Name";
	public static final String DATE = "Date";
	public static final String POINTS = "Records Points";
	public static final String SHARED_PREFERENCES_NAME = "last_record_won";

	private Chronometer chronometer;
	private ImageView hangingImageView;
	private TextView recordTextView;
	private TextView wordTextView;

	private HangGame hangGame;

	private Category category;
	private CategoryWords categoryWords;
	private Player player;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_game_board);

		this.extractBundleExtra(super.getIntent());
		this.findWordForCategory();
		this.createViewComponents();

		this.hangGame = new HangGame(this.category, this.categoryWords,
				this.player, super.getResources().getInteger(
						R.integer.initial_bonus_points), super.getResources()
						.getInteger(R.integer.punishment_bonus_points), null);
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
						Log.d(GameBoardActivity.TAG, "Finishing Activity: "
								+ GameBoardActivity.class.getSimpleName());

						GameBoardActivity.this.chronometer.stop();
						GameBoardActivity.this.finish();
					}
				});
		alertDialogBuilder.setNegativeButton(
				super.getResources().getString(R.string.cancel),
				new DialogInterface.OnClickListener() {

					@Override()
					public void onClick(DialogInterface dialog, int id) {
						GameBoardActivity.this.chronometer.start();
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

		Log.d(TAG, "TextView Letter Pressed: " + button.getText().toString());

		this.revealLetter(button.getText().charAt(0));

		button.setEnabled(false);
		button.setTextColor(super.getResources().getColor(
				R.color.key_button_wrong_text_color));
	}

	private void createViewComponents() {
		super.setTitle(this.category.getName());

		this.chronometer = (Chronometer) super.findViewById(R.id.chronometer);
		this.chronometer.setBase(SystemClock.elapsedRealtime());

		this.hangingImageView = (ImageView) super
				.findViewById(R.id.hanging_progress_image_view);

		this.recordTextView = (TextView) super
				.findViewById(R.id.bonus_text_view);

		this.wordTextView = (TextView) super
				.findViewById(R.id.hidden_word_text_view);
		this.wordTextView.setText(this.maskWord());
	}

	private void extractBundleExtra(Intent intent) {
		Log.d(GameBoardActivity.TAG, "Extracting Intent's Data.");

		Bundle bundle = intent.getExtras();

		this.category = bundle
				.getParcelable(CategoriesDashboardActivity.CATEGORY_SELECTED);
		this.player = bundle.getParcelable(CategoriesDashboardActivity.PLAYER);
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
						Log.e(GameBoardActivity.TAG,
								"No Categories Words Found.");

						GameBoardActivity.this.finish();
					}
				});
				(alertDialogBuilder.create()).show();
			} else {
				this.categoryWords = categoriesWords.get(0);

				Log.d(GameBoardActivity.TAG, "Word Selected: "
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
			string.setCharAt(i, GameBoardActivity.MASK.charAt(0));
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
			this.hangGame.doPunishment();
			this.recordTextView.setText(Integer.toString(this.hangGame
					.getScore()));

			this.hangingImageView.setImageResource(this.hangGame
					.obtainDrawableByState(this.hangGame.getState()));
		} else {
			this.wordTextView.setText(string);
		}

		if ((this.hangGame.isLost())
				|| (string.indexOf(GameBoardActivity.MASK) == -1)) {
			this.chronometer.stop();
			this.hangGame.setTime(this.chronometer.getText().toString());
			this.hangGame.setScore(this.computeTotalScore());

			this.startFinalGameActivity();
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void saveLastRecordIntoSharedPreferences() {
		Log.d(GameBoardActivity.TAG, "Saving Shared Preferences: "
				+ GameBoardActivity.SHARED_PREFERENCES_NAME);

		SharedPreferences sharedPreferences = super
				.getSharedPreferences(
						GameBoardActivity.SHARED_PREFERENCES_NAME,
						Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();

		editor.putString(GameBoardActivity.CATEGORY, this.hangGame
				.getCategory().getName());
		editor.putString(GameBoardActivity.CATEGORY_WORDS, this.hangGame
				.getCategoryWords().getWord1().getName());
		editor.putString(GameBoardActivity.DATE, (new SimpleDateFormat(
				DATE_FORMAT_PATTERN).format(new Date())));
		editor.putInt(GameBoardActivity.POINTS, this.hangGame.getScore());

		editor.commit();
	}

	private void startFinalGameActivity() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(GameBoardActivity.CURRENT_HANG_GAME, this.hangGame);
		bundle.putParcelable(GameBoardActivity.WEB_SERVER_CONFIG,
				this.webServiceServer);

		Intent intent = new Intent(this, GameFinalActivity.class);
		intent.putExtras(bundle);

		if (this.hangGame.isLost() == false) {
			this.saveLastRecordIntoSharedPreferences();
		}

		Log.d(GameBoardActivity.TAG, "Starting Activity: "
				+ GameFinalActivity.class.getSimpleName());

		super.finish();
		super.startActivity(intent);
	}

	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	private int computeTotalScore() {
		Log.i(GameBoardActivity.TAG, "Computing The Score Points.");

		int seconds = 0;
		try {
			seconds = (new SimpleDateFormat("MM:SS")).parse(
					this.hangGame.getTime()).getSeconds();

			if (seconds == 0) {
				seconds++;
			}
		} catch (ParseException e) {
			seconds = 1;
			e.printStackTrace();
		}

		return (this.hangGame.getCategoryWords().getCategoryWordsPK().getWord()
				.length()
				* this.hangGame.getScore() / seconds);
	}
}