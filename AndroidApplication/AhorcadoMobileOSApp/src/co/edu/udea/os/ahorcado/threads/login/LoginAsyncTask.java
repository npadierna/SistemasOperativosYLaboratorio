package co.edu.udea.os.ahorcado.threads.login;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IPlayerWS;
import co.edu.udea.os.ahorcado.service.webservice.impl.PlayerWS;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class LoginAsyncTask extends AsyncTask<Object, Void, Player> {

	private ProgressDialog progressDialog;
	private WebServiceServer webServiceServer;

	public LoginAsyncTask(WebServiceServer webServiceServer,
			ProgressDialog progressDialog) {
		super();
		this.progressDialog = progressDialog;
		this.webServiceServer = webServiceServer;
	}

	@Override()
	protected Player doInBackground(Object... args) {
		IPlayerWS playerWS = new PlayerWS(this.webServiceServer);
		Player player = null;

		try {
			player = playerWS.findPlayerByLogin((String) args[0],
					(String) args[1]);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return (player);
	}

	@Override()
	protected void onCancelled() {
		super.onCancelled();
		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPostExecute(Player result) {
		super.onPostExecute(result);
		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPreExecute() {
		super.onPreExecute();
		this.progressDialog.show();
	}
}