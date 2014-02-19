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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.categories.CategoriesDashboardActivity;
import co.edu.udea.os.ahorcado.activities.categories.CategoryImage;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.persistence.entity.RecordPK;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.record.RecordAsyncTask;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class GameFinalActivity extends Activity {

	private static final String TAG = GameFinalActivity.class.getSimpleName();

	private HangGame hangGame;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_game_final);

		this.extractBundleExtra(super.getIntent());
		this.createViewComponents();

		if (this.hangGame.isLost() == false) {
			this.saveRecordForPlayer();
		}
	}

	@Override()
	public void onBackPressed() {
		Log.d(GameFinalActivity.TAG, "Back Button Pressed. Do Nothing.");
	}

	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	private int computeTotalScore() {
		Log.i(GameFinalActivity.TAG, "Computing The Score Points.");

		int minutes = 0;
		try {
			minutes = (new SimpleDateFormat("MM:SS")).parse(
					this.hangGame.getTime()).getMinutes();

			if (minutes == 0) {
				minutes++;
			}
		} catch (ParseException e) {
			minutes = 1;
			e.printStackTrace();
		}

		return (this.hangGame.getScore()
				* this.hangGame.getCategoryWords().getCategoryWordsPK()
						.getWord().length()
				* (HangGame.RIGHT_LEG - this.hangGame.getState()) / (minutes * 10));
	}

	private void createViewComponents() {
		if (this.hangGame.isLost()) {
			((TextView) super.findViewById(R.id.final_result_text_view))
					.setText(R.string.final_result_lost);
		}

		TextView categoryNameTextView = (TextView) super
				.findViewById(R.id.categoy_name_text_view);

		categoryNameTextView.setText(this.hangGame.getCategory().getName());
		categoryNameTextView.setCompoundDrawablesWithIntrinsicBounds(
				0,
				0,
				0,
				CategoryImage.getInstance().getDrawableByCategoryImageName(
						this.hangGame.getCategory().getImageName()));

		((TextView) super.findViewById(R.id.word_text_view))
				.setText(this.hangGame.getCategoryWords().getCategoryWordsPK()
						.getWord());

		((TextView) super.findViewById(R.id.final_record_text_view))
				.setText(Integer.toString(this.hangGame.getScore()));

		((TextView) super.findViewById(R.id.time_consumed_text_view))
				.setText(this.hangGame.getTime());
	}

	private void extractBundleExtra(Intent intent) {
		Log.d(GameFinalActivity.TAG, "Extracting Intent's Data.");

		Bundle bundle = intent.getExtras();

		this.hangGame = bundle
				.getParcelable(GameBoardActivity.CURRENT_HANG_GAME);
		this.webServiceServer = bundle
				.getParcelable(GameBoardActivity.WEB_SERVER_CONFIG);
	}

	public void onPlayAgain(View view) {
		Log.d(GameFinalActivity.TAG, "Starting Activity: "
				+ CategoriesDashboardActivity.class.getSimpleName());

		super.finish();
	}

	private void saveRecordForPlayer() {
		Log.d(GameFinalActivity.TAG, "Saving New Best Record...");

		int score = this.computeTotalScore();
		if ((this.hangGame.getCategoryWords().getRecord() == null)
				|| (this.hangGame.getCategoryWords().getRecord().getPoints() < score)) {

			Record record = new Record(new RecordPK(this.hangGame.getPlayer()
					.getUserName(), this.hangGame.getCategory().getName(),
					this.hangGame.getCategoryWords().getCategoryWordsPK()
							.getWord()), score, new Date());

			ProgressDialog progressDialog = (new ProgressBarCustomized(this))
					.createProgressDialog(
							super.getResources()
									.getString(
											R.string.saving_record_title_progress_dialog),
							super.getResources()
									.getString(
											R.string.saving_record_text_progress_dialog),
							false);

			RecordAsyncTask recordAsyncTask = new RecordAsyncTask(
					this.webServiceServer, progressDialog,
					RecordAsyncTask.SAVE_BEST_RECORD_FOR_PLAYER);

			recordAsyncTask.execute(new Object[] { record });
			try {
				List<Record> records = recordAsyncTask.get();

				if ((records == null) || (records.isEmpty())) {
					AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
							this))
							.createAlertDialog(
									super.getResources()
											.getString(
													R.string.error_saving_record_title_alert_dialog),
									super.getResources()
											.getString(
													R.string.error_saving_record_text_alert_dialog),
									true);
					alertDialogBuilder.setPositiveButton(super.getResources()
							.getString(R.string.accept), null);
					(alertDialogBuilder.create()).show();
				} else {
					Log.d(GameFinalActivity.TAG,
							"New Best Record Saved Successfully.");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}