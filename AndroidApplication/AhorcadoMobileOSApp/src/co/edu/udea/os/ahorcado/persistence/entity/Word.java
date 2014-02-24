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
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class Word implements IEntityContext, IJSONContext, Parcelable,
		Serializable {

	private static final long serialVersionUID = 4061688705156592640L;

	private static final String NAME = "name";

	private String name;
	private List<CategoryWords> categoryWordsList;

	public Word() {
		super();
	}

	public Word(String name) {
		this.name = name;
	}

	public Word(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
	}

	public Word(Parcel parcel) {
		this.setName(parcel.readString());
	}

	public String getName() {

		return (this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryWords> getCategoryWordsList() {

		return (this.categoryWordsList);
	}

	public void setCategoryWordsList(List<CategoryWords> categoryWordsList) {
		this.categoryWordsList = categoryWordsList;
	}

	@Override()
	public Object getKey() {

		return (this.name);
	}

	@Override()
	public void setKey(Object key) {
		this.name = (String) key;
	}

	@Override()
	public JSONObject packEntityToJsonObject(IJSONContext entityContext)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Word.NAME, this.getName());

		return (jsonObject);
	}

	@Override()
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {
		this.setCategoryWordsList(null);
		this.setName(jsonObject.getString(Word.NAME));

		return (this);
	}

	@Override()
	public int describeContents() {

		return (0);
	}

	@Override()
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.getName());
	}

	@Override()
	public int hashCode() {
		int hash = 0;
		hash += (name != null ? name.hashCode() : 0);

		return (hash);
	}

	@Override()
	public boolean equals(Object object) {
		if (!(object instanceof Word)) {

			return (false);
		}

		Word other = (Word) object;
		if ((this.name == null && other.name != null)
				|| (this.name != null && !this.name.equals(other.name))) {

			return (false);
		}

		return (true);
	}

	@Override()
	public String toString() {

		return ("co.edu.udea.os.ahorcado.persistence.entity.Word[ name="
				+ this.name + " ]");
	}

	public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {

		@Override()
		public Word createFromParcel(Parcel source) {

			return (new Word(source));
		}

		@Override()
		public Word[] newArray(int size) {

			return (new Word[size]);
		}
	};
}