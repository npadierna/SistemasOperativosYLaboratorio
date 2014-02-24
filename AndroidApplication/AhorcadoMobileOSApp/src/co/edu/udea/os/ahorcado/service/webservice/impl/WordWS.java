package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IWordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class WordWS extends WebServiceContext implements IWordWS {

	private static final String TAG = WordWS.class.getSimpleName();

	public WordWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Word> findAllWords() throws URISyntaxException,
			ClientProtocolException, IOException, JSONException {
		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super.executeHTTPMethod(
				new String[] { WebServicePath.WordWSContext.ROOT_PATH }, null,
				get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toWordsListFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	private List<Word> toWordsListFromJSONArray(JSONArray jsonArray)
			throws JSONException {
		List<Word> words = new ArrayList<Word>();

		for (int index = 0; index < jsonArray.length(); index++) {
			words.add(new Word(jsonArray.getJSONObject(index)));
		}

		return (words);
	}
}