package co.edu.udea.os.ahorcado.activities.bestrecords;

import android.widget.ImageView;
import android.widget.TextView;

final class RecordViewHolder {

	private ImageView categoryImageView;
	private TextView categoryNameTextView;
	private TextView playerUserNameTextView;
	private TextView recordTextView;

	public RecordViewHolder() {
		super();
	}

	public ImageView getCategoryImageView() {

		return (this.categoryImageView);
	}

	public void setCategoryImageView(ImageView categoryImageView) {
		this.categoryImageView = categoryImageView;
	}

	public TextView getCategoryNameTextView() {

		return (this.categoryNameTextView);
	}

	public void setCategoryNameTextView(TextView categoryNameTextView) {
		this.categoryNameTextView = categoryNameTextView;
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
}