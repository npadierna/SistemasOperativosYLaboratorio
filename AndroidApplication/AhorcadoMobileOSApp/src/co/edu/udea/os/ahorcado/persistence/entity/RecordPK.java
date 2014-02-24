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
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class RecordPK implements IJSONContext, Parcelable, Serializable {

	private static final long serialVersionUID = 1975712992744627200L;

	private static final String CATEGORY = "category";
	private static final String USER_NAME = "userName";
	private static final String WORD = "word";

	private String userName;
	private String category;
	private String word;

	public RecordPK() {
		super();
	}

	public RecordPK(String userName, String category, String word) {
		this.userName = userName;
		this.category = category;
		this.word = word;
	}

	public RecordPK(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
	}

	public RecordPK(Parcel parcel) {
		this.setCategory(parcel.readString());
		this.setUserName(parcel.readString());
		this.setWord(parcel.readString());
	}

	public String getUserName() {

		return (this.userName);
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

		jsonObject.put(RecordPK.CATEGORY, this.getCategory());
		jsonObject.put(RecordPK.USER_NAME, this.getUserName());
		jsonObject.put(RecordPK.WORD, this.getWord());

		return (jsonObject);
	}

	@Override()
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {
		this.setCategory(jsonObject.getString(RecordPK.CATEGORY));
		this.setUserName(jsonObject.getString(RecordPK.USER_NAME));
		this.setWord(jsonObject.getString(RecordPK.WORD));

		return (this);
	}

	@Override()
	public int describeContents() {

		return (0);
	}

	@Override()
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.getCategory());
		dest.writeString(this.getUserName());
		dest.writeString(this.getWord());
	}

	@Override()
	public int hashCode() {
		int hash = 0;
		hash += (userName != null ? userName.hashCode() : 0);
		hash += (category != null ? category.hashCode() : 0);
		hash += (word != null ? word.hashCode() : 0);

		return (hash);
	}

	@Override()
	public boolean equals(Object object) {
		if (!(object instanceof RecordPK)) {

			return (false);
		}

		RecordPK other = (RecordPK) object;
		if ((this.userName == null && other.userName != null)
				|| (this.userName != null && !this.userName
						.equals(other.userName))) {

			return (false);
		}

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

		return ("co.edu.udea.os.ahorcado.persistence.entity.RecordPK[ userName="
				+ userName + ", category=" + category + ", word=" + word + " ]");
	}

	public static final Parcelable.Creator<RecordPK> CREATOR = new Parcelable.Creator<RecordPK>() {

		@Override()
		public RecordPK createFromParcel(Parcel source) {

			return (new RecordPK(source));
		}

		@Override()
		public RecordPK[] newArray(int size) {

			return (new RecordPK[size]);
		}
	};
}