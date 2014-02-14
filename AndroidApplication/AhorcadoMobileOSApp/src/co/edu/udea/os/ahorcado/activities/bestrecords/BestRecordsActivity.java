package co.edu.udea.os.ahorcado.activities.bestrecords;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.login.LoginActivity;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.record.RecordAsyncTask;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BestRecordsActivity extends Activity {

	private List<Record> records = null;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_best_records);

		this.extractBundleExtra(super.getIntent());
		this.findRecordsForCategories();
		this.createViewComponents();
	}

	private void createViewComponents() {
		ListView listView = (ListView) super
				.findViewById(R.id.best_records_list_view);

		if (!this.records.isEmpty()) {
			ArrayAdapter<Record> arrayAdapter = new RecordsArrayAdapter(this,
					R.layout.adapter_best_records, this.records);

			listView.setAdapter(arrayAdapter);
		} else {
			listView.setVisibility(View.GONE);

			((TextView) super
					.findViewById(R.id.no_best_records_found_text_view))
					.setVisibility(View.VISIBLE);
		}
	}

	private void extractBundleExtra(Intent intent) {
		this.webServiceServer = intent.getExtras().getParcelable(
				LoginActivity.WEB_SERVER_CONFIG);
	}

	private void findRecordsForCategories() {
		ProgressDialog progressDialog = (new ProgressBarCustomized(this))
				.createProgressDialog(
						super.getResources().getString(
								R.string.best_records_title_progress_dialog),
						super.getResources().getString(
								R.string.best_records_text_progress_dialog),
						false);
		RecordAsyncTask recordAsyncTask = new RecordAsyncTask(
				this.webServiceServer, progressDialog,
				RecordAsyncTask.BEST_RECORDS_FOR_ALL_CATEGORIES);

		recordAsyncTask.execute();
		try {
			this.records = recordAsyncTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		if (this.records == null) {
			this.records = new ArrayList<Record>();
		}
	}
}