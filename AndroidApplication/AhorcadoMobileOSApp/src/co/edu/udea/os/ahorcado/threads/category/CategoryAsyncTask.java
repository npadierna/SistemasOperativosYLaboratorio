package co.edu.udea.os.ahorcado.threads.category;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWS;
import co.edu.udea.os.ahorcado.service.webservice.impl.CategoryWS;

public class CategoryAsyncTask extends AsyncTask<Object, Void, List<Category>> {

	public static final int ALL_CATEGORIES = 0;

	private int type;
	private ProgressDialog progressDialog;
	private WebServiceServer webServiceServer;

	public CategoryAsyncTask(WebServiceServer webServiceServer,
			ProgressDialog progressDialog, int type) {
		super();
		this.type = type;
		this.progressDialog = progressDialog;
		this.webServiceServer = webServiceServer;
	}

	public int getType() {

		return (this.type);
	}

	@Override()
	protected List<Category> doInBackground(Object... args) {
		ICategoryWS categoryWS = new CategoryWS(this.webServiceServer);
		List<Category> categories = null;

		try {
			switch (this.getType()) {
			case (CategoryAsyncTask.ALL_CATEGORIES):
				categories = categoryWS.findAllCategories();
				break;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return (categories);
	}

	@Override()
	protected void onCancelled() {
		super.onCancelled();
		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPostExecute(List<Category> result) {
		super.onPostExecute(result);
		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPreExecute() {
		super.onPreExecute();
		this.progressDialog.show();
	}
}