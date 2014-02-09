package co.edu.udea.os.ahorcado.activities.login;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import co.edu.udea.os.ahorcado.threads.login.LoginAsyncTask;

public class LoginActivity extends Activity {

	private static final String TAG = LoginActivity.class.getSimpleName();

	private Player player = null;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_login);

		if (!this.setServerConfig()) {
			AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
					this)).createAlertDialog("Test", "This is a test.", false);
			alertDialogBuilder.setPositiveButton("Okay",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							LoginActivity.this.finish();
						}
					});
			(alertDialogBuilder.create()).show();
		}
	}

	public void onCreateAccount(View view) {
	}

	public void onLogin(View view) {
		String userName = ((EditText) super
				.findViewById(R.id.user_name_edit_text)).getText().toString();
		String password = ((EditText) super
				.findViewById(R.id.password_edit_text)).getText().toString();

		Log.d(TAG, "User Name: " + userName);
		Log.d(TAG, "Password: " + password);

		this.executeLogin(userName, password);
	}

	private void executeLogin(String userName, String password) {
		if ((userName != null) && (password != null)) {
			userName = userName.trim();

			ProgressDialog progressDialog = (new ProgressBarCustomized(this))
					.createProgressDialog("Test", "Testing", false);
			LoginAsyncTask loginAsyncTask = new LoginAsyncTask(
					this.webServiceServer, progressDialog);

			loginAsyncTask.execute(new Object[] { userName, password });
			try {
				this.player = loginAsyncTask.get();
				if (this.player != null) {
					Intent intent = new Intent(this, DashboardActivity.class);
					super.startActivity(intent);
				} else {
					AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
							this)).createAlertDialog("Test", "This is a test.",
							false);
					alertDialogBuilder.setPositiveButton("Okay",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
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