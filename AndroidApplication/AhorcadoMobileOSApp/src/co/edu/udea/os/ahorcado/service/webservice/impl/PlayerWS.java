package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IPlayerWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class PlayerWS extends WebServiceContext implements IPlayerWS {

	private static final String TAG = PlayerWS.class.getSimpleName();

	public PlayerWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public Player findPlayerByLogin(String userName, String password)
			throws URISyntaxException, ClientProtocolException, IOException,
			JSONException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", userName);
		parameters.put("password", password);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(super.buildURIForHTTPMethod(new String[] {
				WebServicePath.PlayerWSContext.ROOT_PATH,
				WebServicePath.PlayerWSContext.PLAYER_LOGIN_PATH }, parameters));

		get.setHeader(WebServiceContext.CONTENT_TYPE_KEY,
				WebServiceContext.CONTENT_TYPE_VALUE);

		HttpResponse httpResponse = httpClient.execute(get);
		if (httpResponse.getEntity() != null) {
			String stringResponse = EntityUtils.toString(httpResponse
					.getEntity());

			Log.d(TAG, "Response: " + stringResponse);

			return (new Player(new JSONObject(stringResponse)));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}
}