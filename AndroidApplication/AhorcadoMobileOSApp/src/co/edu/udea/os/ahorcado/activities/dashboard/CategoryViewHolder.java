package co.edu.udea.os.ahorcado.activities.dashboard;

import android.widget.TextView;

final class CategoryViewHolder {

	private TextView categoryTextView;
	
	public CategoryViewHolder() {
		super();
	}

	public TextView getCategoryTextView() {

		return (this.categoryTextView);
	}

	public void setCategoryTextView(TextView categoryTextView) {
		this.categoryTextView = categoryTextView;
	}
}