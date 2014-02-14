package co.edu.udea.os.ahorcado.activities.login;

import java.io.IOException;
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
import co.edu.udea.os.ahorcado.activities.dashboard.DashboardActivity;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.IServerConfiguration;
import co.edu.udea.os.ahorcado.service.config.impl.ServerConfiguration;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.player.LoginAsyncTask;

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
	}

	public void onLogin(View view) {
		String password = this.passwordEditText.getText().toString();
		String userName = this.userNameEditText.getText().toString();

		Log.d(TAG, "User Name: " + userName);
		Log.d(TAG, "Password: " + password);

		this.executeLogin(userName, password);
	}

	private void createViewComponents() {
		this.passwordEditText = (EditText) super
				.findViewById(R.id.password_edit_text);
		this.userNameEditText = (EditText) super
				.findViewById(R.id.user_name_edit_text);
	}

	private void executeLogin(String userName, String password) {
		if ((userName != null) && (password != null)) {
			userName = userName.trim();

			ProgressDialog progressDialog = (new ProgressBarCustomized(this))
					.createProgressDialog(
							super.getResources().getString(
									R.string.login_title_progress_dialog),
							super.getResources().getString(
									R.string.login_text_progress_dialog), false);
			LoginAsyncTask loginAsyncTask = new LoginAsyncTask(
					this.webServiceServer, progressDialog);

			loginAsyncTask.execute(new Object[] { userName, password });
			try {
				this.player = loginAsyncTask.get();
				if (this.player != null) {
					Bundle bundle = new Bundle();
					bundle.putParcelable(PLAYER, this.player);
					bundle.putParcelable(WEB_SERVER_CONFIG,
							this.webServiceServer);

					Intent intent = new Intent(this, DashboardActivity.class);
					intent.putExtras(bundle);

					super.finish();
					super.startActivity(intent);
				} else {
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
							passwordEditText.setText("");
							userNameEditText.requestFocus();
							userNameEditText.setText("");
						}
					});
					(alertDialogBuilder.create()).show();
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