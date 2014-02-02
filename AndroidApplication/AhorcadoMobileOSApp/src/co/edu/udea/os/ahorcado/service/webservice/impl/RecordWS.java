package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class RecordWS extends WebServiceContext implements IRecordWS {

	private static final String TAG = RecordWS.class.getSimpleName();

	public RecordWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Record> findAllRecordsForPlayer(String playerUserName)
			throws URISyntaxException, ClientProtocolException, IOException,
			JSONException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", playerUserName);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(super.buildURIForHTTPMethod(new String[] {
				WebServicePath.RecordWSContext.ROOT_PATH,
				WebServicePath.RecordWSContext.PLAYER_ALL_RECORDS_PATH },
				parameters));

		get.setHeader(WebServiceContext.CONTENT_TYPE_KEY,
				WebServiceContext.CONTENT_TYPE_VALUE);

		HttpResponse httpResponse = httpClient.execute(get);
		String stringResponse = EntityUtils.toString(httpResponse.getEntity());

		Log.d(TAG, "Response: " + stringResponse);

		return (this.toRecordsArrayFromJSONArray(new JSONArray(super
				.formatToJSONArrayString(stringResponse))));
	}

	@Override()
	public Record findBestRecordForPlayerInCategory(String playerUserName,
			String categoryName) {

		return (null);
	}

	@Override()
	public boolean saveBestRecordForPlayer(Record record) {

		return (false);
	}

	@Override()
	public Record findBestRecordForCategory(String categoryName) {

		return (null);
	}

	@Override()
	public List<Record> findBestRecordsForAllCategories() {

		return (null);
	}

	private List<Record> toRecordsArrayFromJSONArray(JSONArray jsonArray)
			throws JSONException {
		List<Record> records = new ArrayList<Record>();

		for (int index = 0; index < jsonArray.length(); index++) {
			records.add(new Record(jsonArray.getJSONObject(index)));
		}

		return (records);
	}
}