package co.edu.udea.os.ahorcado.activities.dashboard;

import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.login.LoginActivity;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class DashboardActivity extends Activity {

	private Player player;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_dashboard);

		this.extractBundleExtra(super.getIntent());
	}

	private void extractBundleExtra(Intent intent) {
		Bundle bundle = intent.getExtras();

		this.player = bundle.getParcelable(LoginActivity.PLAYER);
		this.webServiceServer = bundle
				.getParcelable(LoginActivity.WEB_SERVER_CONFIG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.getMenuInflater().inflate(R.menu.game_main, menu);
		
		return true;
	}
}