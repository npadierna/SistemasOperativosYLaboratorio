package co.edu.udea.os.ahorcado.activities.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.bestrecords.BestRecordsActivity;
import co.edu.udea.os.ahorcado.activities.login.LoginActivity;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.activities.util.ProgressBarCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.threads.category.CategoryAsyncTask;

public class DashboardActivity extends Activity {

	private static final String TAG = DashboardActivity.class.getSimpleName();

	public static final String CATEGORY_NAME = "Category Name";
	public static final String DATE = "Date";
	public static final String POINTS = "Records Points";
	public static final String SHARED_PREFERENCES_NAME = "last_record_won";
	public static final String WORD = "Word";

	private List<Category> categories = null;
	private Player player;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_dashboard);

		this.extractBundleExtra(super.getIntent());
		this.findAllCategories();
		this.createViewComponents();
	}

	@Override()
	public void onBackPressed() {
		AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
				this)).createAlertDialog(
				super.getResources().getString(
						R.string.leave_dashboard_title_alert_dialog),
				super.getResources().getString(
						R.string.leave_dashboard_text_alert_dialog), true);
		alertDialogBuilder.setPositiveButton(
				super.getResources().getString(R.string.accept),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						DashboardActivity.this.finish();
					}
				});
		alertDialogBuilder.setNegativeButton(
				super.getResources().getString(R.string.cancel), null);
		(alertDialogBuilder.create()).show();
	}

	@Override()
	public boolean onCreateOptionsMenu(Menu menu) {
		super.getMenuInflater().inflate(R.menu.dashboard, menu);

		return (true);
	}

	@Override()
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (R.id.show_all_records):
			Log.d(TAG, "Show Best Records Item Option selected.");
			Bundle bundle = new Bundle();
			bundle.putParcelable(LoginActivity.WEB_SERVER_CONFIG,
					this.webServiceServer);

			Intent intent = new Intent(this, BestRecordsActivity.class);
			intent.putExtras(bundle);

			super.startActivity(intent);
			break;

		case (R.id.show_last_record):
			Log.d(TAG, "Show Last Record Won Item Option selected.");
			this.readSharedPreferences();
			break;

		default:
			return super.onOptionsItemSelected(item);
		}

		return (true);
	}

	private void createViewComponents() {
		TextView titleTextView = (TextView) super
				.findViewById(R.id.user_name_text_view);

		Editable editable = Editable.Factory.getInstance().newEditable(
				titleTextView.getText());
		int length = editable.length();

		editable.append(' ');
		editable.append(this.player.getUserName());
		editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
				length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		titleTextView.setText(editable);

		GridView gridView = (GridView) super
				.findViewById(R.id.categories_grid_view);

		if (!this.categories.isEmpty()) {
			ArrayAdapter<Category> arrayAdapter = new CategoriesArrayAdapter(
					this, R.layout.adapter_categories, this.categories);

			gridView.setAdapter(arrayAdapter);
			gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override()
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Log.d(TAG, "Category Selected: "
							+ categories.get(position).getName());
				}
			});
		} else {

		}
	}

	private void findAllCategories() {
		ProgressDialog progressDialog = (new ProgressBarCustomized(this))
				.createProgressDialog(
						super.getResources().getString(
								R.string.best_records_title_progress_dialog),
						super.getResources().getString(
								R.string.best_records_text_progress_dialog),
						false);
		CategoryAsyncTask categoryAsyncTask = new CategoryAsyncTask(
				this.webServiceServer, progressDialog,
				CategoryAsyncTask.ALL_CATEGORIES);

		categoryAsyncTask.execute();
		try {
			this.categories = categoryAsyncTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		if (this.categories == null) {
			this.categories = new ArrayList<Category>();
		}
	}

	private void extractBundleExtra(Intent intent) {
		Bundle bundle = intent.getExtras();

		this.player = bundle.getParcelable(LoginActivity.PLAYER);
		this.webServiceServer = bundle
				.getParcelable(LoginActivity.WEB_SERVER_CONFIG);
	}

	private void readSharedPreferences() {
		SharedPreferences sharedPreferences = super
				.getSharedPreferences(
						DashboardActivity.SHARED_PREFERENCES_NAME,
						Context.MODE_PRIVATE);

		int record = sharedPreferences.getInt(DashboardActivity.POINTS, -1);
		String categoryName = sharedPreferences.getString(
				DashboardActivity.CATEGORY_NAME, null);
		String date = sharedPreferences.getString(DashboardActivity.DATE, null);
		String word = sharedPreferences.getString(DashboardActivity.WORD, null);

		if ((record != -1) && (categoryName != null) && (date != null)
				&& (word != null)) {
			StringBuilder string = new StringBuilder();
			String[] data = super.getResources().getStringArray(
					R.array.last_record_wont_text_alert_dialog);

			string.append(data[0]).append(" ").append(categoryName)
					.append("\n");
			string.append(data[1]).append(" ").append(date).append("\n");
			string.append(data[2]).append(" ").append(Integer.toString(record))
					.append("\n");
			string.append(data[3]).append(" ").append(word).append("\n");

			AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
					this)).createAlertDialog(
					super.getResources().getString(
							R.string.last_record_wont_title_alert_dialog),
					string.toString(), true);
			alertDialogBuilder.setPositiveButton(super.getResources()
					.getString(R.string.accept), null);
			(alertDialogBuilder.create()).show();
		} else {
			AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
					this))
					.createAlertDialog(
							super.getResources()
									.getString(
											R.string.last_record_wont_title_alert_dialog),
							super.getResources()
									.getString(
											R.string.no_last_record_wont_found_text_alert_dialog),
							true);
			alertDialogBuilder.setPositiveButton(super.getResources()
					.getString(R.string.accept), null);
			(alertDialogBuilder.create()).show();
		}
	}
}