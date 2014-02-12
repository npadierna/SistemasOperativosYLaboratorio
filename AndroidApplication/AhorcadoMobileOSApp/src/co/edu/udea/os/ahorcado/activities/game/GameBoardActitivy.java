package co.edu.udea.os.ahorcado.activities.game;

import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.activities.util.AlertDialogCustomized;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameBoardActitivy extends Activity {

	@Override()
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_game);
	}

	@Override()
	public void onBackPressed() {
		AlertDialog.Builder alertDialogBuilder = (new AlertDialogCustomized(
				this)).createAlertDialog(
				super.getResources().getString(
						R.string.leave_game_title_alert_dialog),
				super.getResources().getString(
						R.string.leave_game_text_alert_dialog), false);
		alertDialogBuilder.setPositiveButton(
				super.getResources().getString(R.string.okay),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						GameBoardActitivy.this.finish();
					}
				});
		alertDialogBuilder.setNegativeButton(
				super.getResources().getString(R.string.no), null);
		(alertDialogBuilder.create()).show();
	}
}