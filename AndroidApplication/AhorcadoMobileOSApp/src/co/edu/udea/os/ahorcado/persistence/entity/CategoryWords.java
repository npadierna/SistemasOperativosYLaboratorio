package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWords implements IEntityContext, IJSONContext, Parcelable,
		Serializable {

	private static final long serialVersionUID = 3074146297965430784L;

	private static final String CATEGORY_WORDS_PK = "categoryWordsPK";

	protected CategoryWordsPK categoryWordsPK;
	private List<Record> recordList;
	private Word word1;
	private Category category1;

	public CategoryWords() {
		super();
	}

	public CategoryWords(CategoryWordsPK categoryWordsPK) {
		this.categoryWordsPK = categoryWordsPK;
	}

	public CategoryWords(String category, String word) {
		this.categoryWordsPK = new CategoryWordsPK(category, word);
	}

	public CategoryWords(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
	}

	public CategoryWords(Parcel parcel) {
		this.setCategoryWordsPK((CategoryWordsPK) parcel
				.readParcelable(CategoryWordsPK.class.getClassLoader()));
	}

	public CategoryWordsPK getCategoryWordsPK() {

		return (this.categoryWordsPK);
	}

	public void setCategoryWordsPK(CategoryWordsPK categoryWordsPK) {
		this.categoryWordsPK = categoryWordsPK;
	}

	public List<Record> getRecordList() {

		return (this.recordList);
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public Word getWord1() {

		return (this.word1);
	}

	public void setWord1(Word word1) {
		this.word1 = word1;
	}

	public Category getCategory1() {

		return (this.category1);
	}

	public void setCategory1(Category category1) {
		this.category1 = category1;
	}

	@Override()
	public Object getKey() {

		return (this.categoryWordsPK);
	}

	@Override()
	public void setKey(Object key) {
		this.categoryWordsPK = (CategoryWordsPK) key;
	}

	@Override()
	public JSONObject packEntityToJsonObject(IJSONContext entityContext)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(CATEGORY_WORDS_PK, this.getCategoryWordsPK()
				.packEntityToJsonObject(this.getCategoryWordsPK()));

		return (jsonObject);
	}

	@Override()
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {
		CategoryWordsPK categoryWordsPK = new CategoryWordsPK(
				jsonObject.getJSONObject(CategoryWords.CATEGORY_WORDS_PK));

		this.setCategory1(new Category(categoryWordsPK.getCategory()));
		this.setCategoryWordsPK(categoryWordsPK);

		this.setWord1(new Word(categoryWordsPK.getWord()));

		return (this);
	}

	@Override()
	public int describeContents() {

		return (0);
	}

	@Override()
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.getCategoryWordsPK(), 0);
	}

	@Override()
	public int hashCode() {
		int hash = 0;
		hash += (categoryWordsPK != null ? categoryWordsPK.hashCode() : 0);

		return (hash);
	}

	@Override()
	public boolean equals(Object object) {
		if (!(object instanceof CategoryWords)) {

			return (false);
		}

		CategoryWords other = (CategoryWords) object;
		if ((this.categoryWordsPK == null && other.categoryWordsPK != null)
				|| (this.categoryWordsPK != null && !this.categoryWordsPK
						.equals(other.categoryWordsPK))) {

			return (false);
		}

		return (true);
	}

	@Override()
	public String toString() {

		return ("co.edu.udea.os.ahorcado.persistence.entity.CategoryWords[ categoryWordsPK="
				+ this.categoryWordsPK + " ]");
	}

	public static final Parcelable.Creator<CategoryWords> CREATOR = new Parcelable.Creator<CategoryWords>() {

		@Override()
		public CategoryWords createFromParcel(Parcel source) {

			return (new CategoryWords(source));
		}

		@Override()
		public CategoryWords[] newArray(int size) {

			return (new CategoryWords[size]);
		}
	};
}