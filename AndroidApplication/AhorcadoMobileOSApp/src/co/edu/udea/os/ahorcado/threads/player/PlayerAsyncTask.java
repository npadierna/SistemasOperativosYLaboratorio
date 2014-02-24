package co.edu.udea.os.ahorcado.threads.player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IPlayerWS;
import co.edu.udea.os.ahorcado.service.webservice.impl.PlayerWS;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class PlayerAsyncTask extends AsyncTask<Object, Void, List<Player>> {

	public static final int LOGIN = 0;

	private int type;
	private ProgressDialog progressDialog;
	private WebServiceServer webServiceServer;

	public PlayerAsyncTask(WebServiceServer webServiceServer,
			ProgressDialog progressDialog, int type) {
		super();

		this.setType(type);

		this.progressDialog = progressDialog;
		this.webServiceServer = webServiceServer;
	}

	public int getType() {

		return (this.type);
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override()
	protected List<Player> doInBackground(Object... args) {
		IPlayerWS playerWS = new PlayerWS(this.webServiceServer);
		List<Player> players = null;
		Player player = null;

		boolean gotIn = false;
		try {
			switch (this.getType()) {
			case (PlayerAsyncTask.LOGIN):
				if (args.length == 2) {
					if ((args[0] != null) && (args[0] instanceof String)
							&& (args[1] != null) && (args[1] instanceof String)) {
						player = playerWS.findPlayerByLogin((String) args[0],
								(String) args[1]);

						gotIn = true;
					}
				}
				break;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if ((gotIn == true) && (player != null)) {
			players = new ArrayList<Player>();
			players.add(player);
		}

		return (players);
	}

	@Override()
	protected void onCancelled() {
		super.onCancelled();

		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPostExecute(List<Player> result) {
		super.onPostExecute(result);

		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPreExecute() {
		super.onPreExecute();

		this.progressDialog.show();
	}
}