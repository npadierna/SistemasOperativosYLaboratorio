package co.edu.udea.os.ahorcado.activities.login;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.categories.CategoriesDashboardActivity;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.IServerConfiguration;
import co.edu.udea.os.ahorcado.service.config.impl.ServerConfiguration;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.player.PlayerAsyncTask;

public class LoginActivity extends Activity {

	private static final String TAG = LoginActivity.class.getSimpleName();

	public static final String PLAYER = "Logged Player";
	public static final String WEB_SERVER_CONFIG = "Web Server Configuration";

	private EditText passwordEditText;
	private EditText userNameEditText;
	private Player player = null;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_login);

		if (!this.setServerConfig()) {
			Log.e(LoginActivity.TAG, "WebServiceServer Not Configured.");

			AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
					this))
					.createAlertDialog(
							super.getResources().getString(
									R.string.server_error_title),
							super.getResources().getString(
									R.string.server_error_text), false);
			alertDialogBuilder.setPositiveButton(super.getResources()
					.getString(R.string.accept),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							LoginActivity.this.finish();
						}
					});
			(alertDialogBuilder.create()).show();
		}

		this.createViewComponents();
	}

	public void onCreateAccount(View view) {
		Log.i(LoginActivity.TAG, "Create Account Button Pressed.");
	}

	public void onLogin(View view) {
		Log.i(LoginActivity.TAG, "Login Button Pressed.");

		String password = this.passwordEditText.getText().toString();
		String userName = this.userNameEditText.getText().toString();

		Log.i(LoginActivity.TAG, "User Name: " + userName);
		Log.i(LoginActivity.TAG, "Password: " + password);

		this.findPlayerByLogin(userName, password);
	}

	private void createViewComponents() {
		this.passwordEditText = (EditText) super
				.findViewById(R.id.password_edit_text);

		this.userNameEditText = (EditText) super
				.findViewById(R.id.user_name_edit_text);
	}

	private void findPlayerByLogin(String userName, String password) {
		if ((userName != null) && (password != null)) {
			userName = userName.trim();

			ProgressDialog progressDialog = (new ProgressBarCustomized(this))
					.createProgressDialog(
							super.getResources().getString(
									R.string.login_title_progress_dialog),
							super.getResources().getString(
									R.string.login_text_progress_dialog), false);

			PlayerAsyncTask playerAsyncTask = new PlayerAsyncTask(
					this.webServiceServer, progressDialog,
					PlayerAsyncTask.LOGIN);

			playerAsyncTask.execute(new Object[] { userName, password });
			try {
				List<Player> players = playerAsyncTask.get();

				if ((players == null) || (players.isEmpty())) {
					AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
							this)).createAlertDialog(
							super.getResources().getString(
									R.string.user_error_title_alert_dialog),
							super.getResources().getString(
									R.string.user_error_text_alert_dialog),
							true);
					alertDialogBuilder.setPositiveButton(super.getResources()
							.getString(R.string.accept), new OnClickListener() {

						@Override()
						public void onClick(DialogInterface dialog, int which) {
							Log.d(LoginActivity.TAG, "Player Not Found.");

							LoginActivity.this.passwordEditText.setText("");
							LoginActivity.this.userNameEditText.requestFocus();
							LoginActivity.this.userNameEditText.setText("");
						}
					});
					(alertDialogBuilder.create()).show();
				} else {
					this.player = players.get(0);

					Log.i(LoginActivity.TAG,
							"Email's Player: " + this.player.getEmail());

					Bundle bundle = new Bundle();
					bundle.putParcelable(LoginActivity.PLAYER, this.player);
					bundle.putParcelable(LoginActivity.WEB_SERVER_CONFIG,
							this.webServiceServer);

					Intent intent = new Intent(this,
							CategoriesDashboardActivity.class);
					intent.putExtras(bundle);

					Log.d(LoginActivity.TAG, "Starting Activity: "
							+ CategoriesDashboardActivity.class.getSimpleName());

					super.finish();
					super.startActivity(intent);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean setServerConfig() {
		IServerConfiguration serverConfig = new ServerConfiguration();
		boolean status = false;

		try {
			this.webServiceServer = serverConfig.loadServerConfiguration(
					super.getApplicationContext(), R.xml.server_config);
			status = true;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (status);
	}
}