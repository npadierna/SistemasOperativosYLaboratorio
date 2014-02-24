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
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class CategoryWS extends WebServiceContext implements ICategoryWS {

	private static final String TAG = CategoryWS.class.getSimpleName();

	public CategoryWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Category> findAllCategories() throws ClientProtocolException,
			IOException, JSONException, URISyntaxException {
		HttpGet get = new HttpGet();
		HttpEntity httpEntity = super.executeHTTPMethod(
				new String[] { WebServicePath.CategoryWSContext.ROOT_PATH },
				null, get);
		if (httpEntity != null) {
			String stringResponse = EntityUtils.toString(httpEntity);

			Log.d(TAG, "Response: " + stringResponse);

			return (this.toCategoriesListFromJSONArray(new JSONArray(super
					.formatToJSONArrayString(stringResponse))));
		}

		Log.d(TAG, "Response: NULL.");

		return (null);
	}

	private List<Category> toCategoriesListFromJSONArray(JSONArray jsonArray)
			throws JSONException {
		List<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < jsonArray.length(); index++) {
			categories.add(new Category(jsonArray.getJSONObject(index)));
		}

		return (categories);
	}
}