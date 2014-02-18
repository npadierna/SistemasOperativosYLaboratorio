package co.edu.udea.os.ahorcado.activities.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.categories.CategoriesDashboardActivity;
import co.edu.udea.os.ahorcado.activities.categories.CategoryImage;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;

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
	}

	@Override()
	public void onBackPressed() {
		Log.d(GameFinalActivity.TAG, "Back Button Pressed. Do Nothing.");
	}

	private void extractBundleExtra(Intent intent) {
		Log.d(GameFinalActivity.TAG, "Extracting Intent's Data.");

		Bundle bundle = intent.getExtras();

		this.hangGame = bundle
				.getParcelable(GameBoardActivity.CURRENT_HANG_GAME);
		this.webServiceServer = bundle
				.getParcelable(GameBoardActivity.WEB_SERVER_CONFIG);
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

		// ((TextView) super.findViewById(R.id.time_consumed_text_view))
		// .setText(this.hangGame.getCategoryWords().getCategoryWordsPK()
		// .getWord());
	}

	public void onPlayAgain(View view) {
		Log.d(GameFinalActivity.TAG, "Starting Activity: "
				+ CategoriesDashboardActivity.class.getSimpleName());

		super.finish();
	}
}