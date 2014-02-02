package co.edu.udea.os.ahorcado.service.webservice;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import co.edu.udea.os.ahorcado.persistence.entity.Player;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface IPlayerWS {

	public Player findPlayerByLogin(String userName, String password)
			throws URISyntaxException, ClientProtocolException, IOException,
			JSONException;
}