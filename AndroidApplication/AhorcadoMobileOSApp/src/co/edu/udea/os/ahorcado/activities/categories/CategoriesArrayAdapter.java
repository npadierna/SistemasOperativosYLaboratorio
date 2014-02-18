package co.edu.udea.os.ahorcado.activities.categories;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.persistence.entity.Category;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
final class CategoriesArrayAdapter extends ArrayAdapter<Category> {

	private int resource;
	private Activity activity;
	private List<Category> categories;

	public CategoriesArrayAdapter(Activity activity, int resource,
			List<Category> categories) {
		super(activity.getApplicationContext(), resource, categories);

		this.setResource(resource);
		this.setActivity(activity);
		this.setCategories(categories);
	}

	@Override()
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		CategoryViewHolder categoryViewHolder;

		if (view == null) {
			LayoutInflater layoutInflater = this.getActivity()
					.getLayoutInflater();

			view = layoutInflater.inflate(this.getResource(), null);

			categoryViewHolder = new CategoryViewHolder();
			categoryViewHolder.setCategoryTextView((TextView) view
					.findViewById(R.id.category_text_view));

			view.setTag(categoryViewHolder);
		} else {
			categoryViewHolder = (CategoryViewHolder) view.getTag();
		}

		this.fillCategoryDetails(position, categoryViewHolder);

		return (view);
	}

	public Activity getActivity() {

		return (this.activity);
	}

	private void setActivity(Activity ownerActivity) {
		this.activity = ownerActivity;
	}

	public int getResource() {

		return (this.resource);
	}

	private void setResource(int resource) {
		this.resource = resource;
	}

	public List<Category> getCategories() {

		return (this.categories);
	}

	private void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	private void fillCategoryDetails(int position,
			CategoryViewHolder categoryViewHolder) {
		Category category = this.getCategories().get(position);
		CategoryImage categoryImage = CategoryImage.getInstance();
		TextView textView = categoryViewHolder.getCategoryTextView();

		textView.setCompoundDrawablesWithIntrinsicBounds(0, categoryImage
				.getDrawableByCategoryImageName(category.getImageName()), 0, 0);
		textView.setText(category.getName());
	}
}