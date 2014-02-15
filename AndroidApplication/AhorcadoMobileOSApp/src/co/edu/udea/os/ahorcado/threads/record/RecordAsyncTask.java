package co.edu.udea.os.ahorcado.threads.record;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;
import co.edu.udea.os.ahorcado.service.webservice.impl.RecordWS;

public class RecordAsyncTask extends AsyncTask<Object, Void, List<Record>> {

	public static final int ALL_RECORDS_FOR_PLAYER = 0;
	public static final int BEST_RECORDS_FOR_ALL_CATEGORIES = 1;
	public static final int BEST_RECORD_FOR_CATEGORY = 2;
	public static final int BEST_RECORD_FOR_PLAYER_IN_CATEGORY = 3;
	public static final int BEST_RECORD_FOR_PLAYER = 4;

	private int type;
	private ProgressDialog progressDialog;
	private WebServiceServer webServiceServer;

	public RecordAsyncTask(WebServiceServer webServiceServer,
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
	protected List<Record> doInBackground(Object... args) {
		IRecordWS recordWS = new RecordWS(this.webServiceServer);
		List<Record> records = null;
		Record record = null;

		boolean gotIn = false;
		try {
			switch (this.getType()) {
			case (RecordAsyncTask.ALL_RECORDS_FOR_PLAYER):
				if ((args[0] != null) && (args[0] instanceof Player)) {
					records = recordWS
							.findAllRecordsForPlayer(((Player) args[0])
									.getUserName());
				}
				break;

			case (RecordAsyncTask.BEST_RECORD_FOR_CATEGORY):
				if ((args[0] != null) && (args[0] instanceof Category)) {
					record = recordWS
							.findBestRecordForCategory(((Category) args[0])
									.getName());

					gotIn = true;
				}
				break;

			case (RecordAsyncTask.BEST_RECORD_FOR_PLAYER):
				if ((args[0] != null) && (args[0] instanceof Record)) {
					record = recordWS.saveBestRecordForPlayer((Record) args[0]);

					gotIn = true;
				}
				break;

			case (RecordAsyncTask.BEST_RECORD_FOR_PLAYER_IN_CATEGORY):
				if (args.length == 2) {
					if ((args[0] != null) && (args[0] instanceof Player)
							&& (args[1] != null)
							&& (args[1] instanceof Category)) {
						record = recordWS.findBestRecordForPlayerInCategory(
								((Player) args[0]).getUserName(),
								((Category) args[1]).getName());

						gotIn = true;
					}
				}
				break;

			case (RecordAsyncTask.BEST_RECORDS_FOR_ALL_CATEGORIES):
				records = recordWS.findBestRecordsForAllCategories();
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

		if ((gotIn == true) && (record != null)) {
			records = new ArrayList<Record>();
			records.add(record);
		}

		return (records);
	}

	@Override()
	protected void onCancelled() {
		super.onCancelled();

		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPostExecute(List<Record> result) {
		super.onPostExecute(result);

		this.progressDialog.dismiss();
	}

	@Override()
	protected void onPreExecute() {
		super.onPreExecute();

		this.progressDialog.show();
	}
}