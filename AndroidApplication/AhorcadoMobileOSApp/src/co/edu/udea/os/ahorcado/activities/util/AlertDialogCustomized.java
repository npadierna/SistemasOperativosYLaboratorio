package co.edu.udea.os.ahorcado.activities.util;

import android.app.Activity;
import android.app.AlertDialog;

public class AlertDialogCustomized {

	private Activity activity;

	public AlertDialogCustomized(Activity activity) {
		super();
		this.setActivity(activity);
	}

	public Activity getActivity() {

		return (this.activity);
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public AlertDialog.Builder createAlertDialog(String title, String message,
			boolean isCancelable) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this.getActivity());

		alertDialogBuilder.setCancelable(isCancelable);
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setTitle(title);

		return (alertDialogBuilder);
	}
}