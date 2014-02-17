package co.edu.udea.os.ahorcado.activities.game;

import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GameFinalActivity extends Activity {

	private static final String TAG = GameFinalActivity.class.getSimpleName();

	private HangGame hangGame;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_game_final);

		this.extractBundleExtra(super.getIntent());
		this.findWordForCategory();
		this.createViewComponents();
	}

	@Override
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

	private void findWordForCategory() {

	}

	private void createViewComponents() {

	}
}