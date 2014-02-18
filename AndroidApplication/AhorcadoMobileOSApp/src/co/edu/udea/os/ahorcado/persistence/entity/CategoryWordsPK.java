package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWordsPK implements IJSONContext, Parcelable, Serializable {

	private static final long serialVersionUID = 8693019297638724608L;

	private static final String CATEGORY = "category";
	private static final String WORD = "word";

	private String category;
	private String word;

	public CategoryWordsPK() {
		super();
	}

	public CategoryWordsPK(String category, String word) {
		this.category = category;
		this.word = word;
	}

	public CategoryWordsPK(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
	}

	public CategoryWordsPK(Parcel parcel) {
		this.setCategory(parcel.readString());
		this.setWord(parcel.readString());
	}

	public String getCategory() {

		return (this.category);
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWord() {

		return (this.word);
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override()
	public JSONObject packEntityToJsonObject(IJSONContext entityContext)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(CategoryWordsPK.CATEGORY, this.getCategory());
		jsonObject.put(CategoryWordsPK.WORD, this.getWord());

		return (jsonObject);
	}

	@Override()
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {
		this.setCategory(jsonObject.getString(CategoryWordsPK.CATEGORY));
		this.setWord(jsonObject.getString(CategoryWordsPK.WORD));

		return (this);
	}

	@Override()
	public int describeContents() {

		return (0);
	}

	@Override()
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.getCategory());
		dest.writeString(this.getWord());
	}

	@Override()
	public int hashCode() {
		int hash = 0;
		hash += (category != null ? category.hashCode() : 0);
		hash += (word != null ? word.hashCode() : 0);

		return (hash);
	}

	@Override()
	public boolean equals(Object object) {
		if (!(object instanceof CategoryWordsPK)) {

			return (false);
		}

		CategoryWordsPK other = (CategoryWordsPK) object;
		if ((this.category == null && other.category != null)
				|| (this.category != null && !this.category
						.equals(other.category))) {

			return (false);
		}

		if ((this.word == null && other.word != null)
				|| (this.word != null && !this.word.equals(other.word))) {

			return (false);
		}

		return (true);
	}

	@Override()
	public String toString() {

		return ("co.edu.udea.os.ahorcado.persistence.entity.CategoryWordsPK[ category="
				+ this.category + ", word=" + this.word + " ]");
	}

	public static final Parcelable.Creator<CategoryWordsPK> CREATOR = new Parcelable.Creator<CategoryWordsPK>() {

		@Override()
		public CategoryWordsPK createFromParcel(Parcel source) {

			return (new CategoryWordsPK(source));
		}

		@Override()
		public CategoryWordsPK[] newArray(int size) {

			return (new CategoryWordsPK[size]);
		}
	};
}