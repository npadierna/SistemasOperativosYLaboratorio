package co.edu.udea.os.ahorcado.activities.categories;

import java.util.HashMap;
import java.util.Map;

import co.edu.udea.os.ahorcado.R;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryImage {

	private Map<String, Integer> mapForImages;
	private static CategoryImage instance;

	private CategoryImage() {
		super();

		this.createMapForImages();
	}

	public synchronized static CategoryImage getInstance() {
		if (instance == null) {
			instance = new CategoryImage();
		}

		return (instance);
	}

	public int getDrawableByCategoryImageName(String categoryImageName) {

		return (this.mapForImages.get(categoryImageName).intValue());
	}

	private void createMapForImages() {
		this.mapForImages = new HashMap<String, Integer>();

		this.mapForImages.put("ic_clothes_category.png",
				Integer.valueOf(R.drawable.ic_clothes_category));
		this.mapForImages.put("ic_education_category.png",
				Integer.valueOf(R.drawable.ic_education_category));
		this.mapForImages.put("ic_foods_category.png",
				Integer.valueOf(R.drawable.ic_foods_category));
		this.mapForImages.put("ic_geography_category.png",
				Integer.valueOf(R.drawable.ic_geography_category));
		this.mapForImages.put("ic_home_category.png",
				Integer.valueOf(R.drawable.ic_home_category));
		this.mapForImages.put("ic_leisure_category.png",
				Integer.valueOf(R.drawable.ic_leisure_category));
		this.mapForImages.put("ic_music_category.png",
				Integer.valueOf(R.drawable.ic_music_category));
		this.mapForImages.put("ic_nature_category.png",
				Integer.valueOf(R.drawable.ic_nature_category));
		this.mapForImages.put("ic_religions_category.png",
				Integer.valueOf(R.drawable.ic_religions_category));
		this.mapForImages.put("ic_sports_category.png",
				Integer.valueOf(R.drawable.ic_sports_category));
		this.mapForImages.put("ic_technology_category.png",
				Integer.valueOf(R.drawable.ic_technology_category));
		this.mapForImages.put("ic_verbs_category.png",
				Integer.valueOf(R.drawable.ic_verbs_category));
	}
}