package co.edu.udea.os.ahorcado.threads.categorywords;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWordsWS;
import co.edu.udea.os.ahorcado.service.webservice.impl.CategoryWordsWS;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWordsAsyncTask extends
		AsyncTask<Object, Void, List<CategoryWords>> {

	public static final int ALL_WORDS_FOR_CATEGORY = 0;
	public static final int ONE_WORD_FOR_CATEGORY = 1;

	private int type;
	private ProgressDialog progressDialog;
	private WebServiceServer webServiceServer;

	public CategoryWordsAsyncTask(WebServiceServer webServiceServer,
			ProgressDialog progressDialog, int type) {
		super();

		this.setType(type);

		this.progressDialog = progressDialog;
		this.webServiceServer = webServiceServer;
	}

	public int getType() {

		return (this.type);
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override()
	protected List<CategoryWords> doInBackground(Object... args) {
		ICategoryWordsWS categoryWordsWs = new CategoryWordsWS(
				this.webServiceServer);
		List<CategoryWords> categoriesWords = null;
		CategoryWords categoryWords = null;

		boolean gotIn = false;
		try {
			switch (this.getType()) {
			case (CategoryWordsAsyncTask.ALL_WORDS_FOR_CATEGORY):
				if ((args[0] != null) && (args[0] instanceof String)) {
					categoriesWords = categoryWordsWs
							.findAllWordsForCategory((String) args[0]);
				}
				break;

			case (CategoryWordsAsyncTask.ONE_WORD_FOR_CATEGORY):
				if ((args[0] != null) && (args[0] instanceof String)) {
					categoryWords = categoryWordsWs
							.findOneWordForCategory((String) args[0]);

					gotIn = true;
				}
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

		if ((gotIn == true) && (categoryWords != null)) {
			categoriesWords = new ArrayList<CategoryWords>();
			categoriesWords.add(categoryWords);
		}

		return (categoriesWords);
	}

	@Override()
	protected void onCancelled() {
		super.onCancelled();

		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPostExecute(List<CategoryWords> result) {
		super.onPostExecute(result);

		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPreExecute() {
		super.onPreExecute();

		this.progressDialog.show();
	}
}