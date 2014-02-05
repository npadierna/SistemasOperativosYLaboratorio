package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
 */
public class WordWS extends WebServiceContext implements IWordWS {

	private static final String TAG = WordWS.class.getSimpleName();

	public WordWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Word> findAllWords() throws URISyntaxException,
			ClientProtocolException, IOException, JSONException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(super.buildURIForHTTPMethod(
				new String[] { WebServicePath.WordWSContext.ROOT_PATH }, null));

		get.setHeader(WebServiceContext.CONTENT_TYPE_KEY,
				WebServiceContext.CONTENT_TYPE_VALUE);

		HttpResponse httpResponse = httpClient.execute(get);
		if (httpResponse.getEntity() != null) {
			String stringResponse = EntityUtils.toString(httpResponse
					.getEntity());

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toWordsArrayFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	private List<Word> toWordsArrayFromJSONArray(JSONArray jsonArray)
			throws JSONException {
		List<Word> words = new ArrayList<Word>();

		for (int index = 0; index < jsonArray.length(); index++) {
			words.add(new Word(jsonArray.getJSONObject(index)));
		}

		return (words);
	}
}