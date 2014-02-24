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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWordsWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class CategoryWordsWS extends WebServiceContext implements
		ICategoryWordsWS {

	private static final String TAG = CategoryWordsWS.class.getSimpleName();

	public CategoryWordsWS(WebServiceServer webServiesServer) {
		super(webServiesServer);
	}

	@Override()
	public List<CategoryWords> findAllWordsForCategory(String categoryName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("categoryname", categoryName);

		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super
				.executeHTTPMethod(
						new String[] {
								WebServicePath.CategoryWordsWSContext.ROOT_PATH,
								WebServicePath.CategoryWordsWSContext.CATEGORY_ALL_WORDS_PATH },
						parameters, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toCategoriesWordsListFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	@Override()
	public CategoryWords findOneWordForCategory(String categoryName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("categoryname", categoryName);

		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super.executeHTTPMethod(new String[] {
				WebServicePath.CategoryWordsWSContext.ROOT_PATH,
				WebServicePath.CategoryWordsWSContext.CATEGORY_ONE_WORD_PATH },
				parameters, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (new CategoryWords(new JSONObject(stringResponse)));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	private List<CategoryWords> toCategoriesWordsListFromJSONArray(
			JSONArray jsonArray) throws JSONException {
		List<CategoryWords> categoriesWords = new ArrayList<CategoryWords>();

		for (int index = 0; index < jsonArray.length(); index++) {
			categoriesWords.add(new CategoryWords(jsonArray
					.getJSONObject(index)));
		}

		return (categoriesWords);
	}
}