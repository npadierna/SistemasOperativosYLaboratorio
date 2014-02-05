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
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWS extends WebServiceContext implements ICategoryWS {

	private static final String TAG = CategoryWS.class.getSimpleName();

	public CategoryWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Category> findAllCategories() throws URISyntaxException,
			ClientProtocolException, IOException, JSONException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(super.buildURIForHTTPMethod(
				new String[] { WebServicePath.CategoryWSContext.ROOT_PATH },
				null));

		get.setHeader(WebServiceContext.CONTENT_TYPE_KEY,
				WebServiceContext.CONTENT_TYPE_VALUE);

		HttpResponse httpResponse = httpClient.execute(get);
		if (httpResponse.getEntity() != null) {
			String stringResponse = EntityUtils.toString(httpResponse
					.getEntity());

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toCategoriesArrayFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	private List<Category> toCategoriesArrayFromJSONArray(JSONArray jsonArray)
			throws JSONException {
		List<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < jsonArray.length(); index++) {
			categories.add(new Category(jsonArray.getJSONObject(index)));
		}

		return (categories);
	}
}