package co.edu.udea.os.ahorcado.activities.categories;

import android.widget.TextView;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
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