package co.edu.udea.os.ahorcado.activities.bestrecords;

import android.widget.TextView;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
final class RecordViewHolder {

	private TextView categoryNameTextView;
	private TextView dateTextView;
	private TextView playerUserNameTextView;
	private TextView recordTextView;
	private TextView wordTextView;

	public RecordViewHolder() {
		super();
	}

	public TextView getCategoryNameTextView() {

		return (this.categoryNameTextView);
	}

	public void setCategoryNameTextView(TextView categoryNameTextView) {
		this.categoryNameTextView = categoryNameTextView;
	}

	public TextView getDateTextView() {

		return (this.dateTextView);
	}

	public void setDateTextView(TextView dateTextView) {
		this.dateTextView = dateTextView;
	}

	public TextView getPlayerUserNameTextView() {

		return (this.playerUserNameTextView);
	}

	public void setPlayerUserNameTextView(TextView playerUserNameTextView) {
		this.playerUserNameTextView = playerUserNameTextView;
	}

	public TextView getRecordTextView() {

		return (this.recordTextView);
	}

	public void setRecordTextView(TextView recordTextView) {
		this.recordTextView = recordTextView;
	}

	public TextView getWordTextView() {

		return (this.wordTextView);
	}

	public void setWordTextView(TextView wordTextView) {
		this.wordTextView = wordTextView;
	}
}