package co.edu.udea.os.ahorcado.activities.dashboard;

import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.login.LoginActivity;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DashboardActivity extends Activity {

	private static final String TAG = DashboardActivity.class.getSimpleName();

	private Player player;
	private WebServiceServer webServiceServer;

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_dashboard);

		this.extractBundleExtra(super.getIntent());
		this.createViewComponents();
	}

	@Override()
	public void onBackPressed() {
		AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
				this)).createAlertDialog(
				super.getResources().getString(
						R.string.leave_dashboard_title_alert_dialog),
				super.getResources().getString(
						R.string.leave_dashboard_text_alert_dialog), false);
		alertDialogBuilder.setPositiveButton(
				super.getResources().getString(R.string.okay),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						DashboardActivity.this.finish();
					}
				});
		alertDialogBuilder.setNegativeButton(
				super.getResources().getString(R.string.no), null);
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
			Log.d(TAG, "Best Records Item Option selected.");
			super.startActivity(new Intent(this, BestRecordsActivity.class));

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
	}

	private void extractBundleExtra(Intent intent) {
		Bundle bundle = intent.getExtras();

		this.player = bundle.getParcelable(LoginActivity.PLAYER);
		this.webServiceServer = bundle
				.getParcelable(LoginActivity.WEB_SERVER_CONFIG);
	}
}