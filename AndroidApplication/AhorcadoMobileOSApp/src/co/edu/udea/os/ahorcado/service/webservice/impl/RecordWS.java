package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class RecordWS extends WebServiceContext implements IRecordWS {

	private static final String TAG = RecordWS.class.getSimpleName();

	public RecordWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Record> findAllRecordsForPlayer(String playerUserName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", playerUserName);

		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super.executeHTTPMethod(new String[] {
				WebServicePath.RecordWSContext.ROOT_PATH,
				WebServicePath.RecordWSContext.PLAYER_ALL_RECORDS_PATH },
				parameters, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toRecordsListFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	@Override()
	public List<Record> findBestRecordsForAllCategories()
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException {
		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super
				.executeHTTPMethod(
						new String[] {
								WebServicePath.RecordWSContext.ROOT_PATH,
								WebServicePath.RecordWSContext.CATEGORY_ALL_BEST_RECORDS_PATH },
						null, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toRecordsListFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	@Override()
	public Record findBestRecordForCategory(String categoryName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("categoryname", categoryName);

		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super.executeHTTPMethod(new String[] {
				WebServicePath.RecordWSContext.ROOT_PATH,
				WebServicePath.RecordWSContext.CATEGORY_BEST_RECORD_PATH },
				parameters, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (new Record(new JSONObject(stringResponse)));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	@Override()
	public Record findBestRecordForPlayerInCategory(String playerUserName,
			String categoryName) throws ClientProtocolException, IOException,
			JSONException, URISyntaxException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", playerUserName);
		parameters.put("categoryname", categoryName);

		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super
				.executeHTTPMethod(
						new String[] {
								WebServicePath.RecordWSContext.ROOT_PATH,
								WebServicePath.RecordWSContext.PLAYER_BEST_RECORD_FOR_CATEGORY_PATH },
						parameters, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (new Record(new JSONObject(stringResponse)));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	@Override()
	public Record saveRecord(Record record) throws ClientProtocolException,
			IOException, JSONException, URISyntaxException {
		JSONObject jsonObject = record.packEntityToJsonObject(record);

		HttpPut put = new HttpPut();
		StringEntity stringEntity = new StringEntity(jsonObject.toString());
		put.setEntity(stringEntity);

		HttpEntity httpEntity = super.executeHTTPMethod(
				new String[] { WebServicePath.RecordWSContext.ROOT_PATH },
				null, put);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (new Record(jsonObject));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	private List<Record> toRecordsListFromJSONArray(JSONArray jsonArray)
			throws JSONException {
		List<Record> records = new ArrayList<Record>();

		for (int index = 0; index < jsonArray.length(); index++) {
			records.add(new Record(jsonArray.getJSONObject(index)));
		}

		return (records);
	}
}