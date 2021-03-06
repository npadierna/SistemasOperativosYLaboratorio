package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class Record implements IEntityContext, IJSONContext, Parcelable,
		Serializable {

	private static final long serialVersionUID = 9084931501438312448L;

	private static final String DATE = "date";
	private static final String POINTS = "points";
	private static final String RECORD_PK = "recordPK";

	protected RecordPK recordPK;
	private int points;
	private Date date;
	private CategoryWords categoryWords;
	private Player player;

	public Record() {
		super();
	}

	public Record(RecordPK recordPK) {
		this.recordPK = recordPK;
	}

	public Record(RecordPK recordPK, int points, Date date) {
		this.recordPK = recordPK;
		this.points = points;
		this.date = date;
	}

	public Record(String userName, String category, String word) {
		this.recordPK = new RecordPK(userName, category, word);
	}

	public Record(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
	}

	public Record(Parcel parcel) {
		this.setDate((Date) parcel.readSerializable());
		this.setPoints(parcel.readInt());
		this.setRecordPK((RecordPK) parcel.readParcelable(RecordPK.class
				.getClassLoader()));
	}

	public RecordPK getRecordPK() {

		return (this.recordPK);
	}

	public void setRecordPK(RecordPK recordPK) {
		this.recordPK = recordPK;
	}

	public int getPoints() {

		return (this.points);
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Date getDate() {

		return (this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Player getPlayer() {

		return (this.player);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public CategoryWords getCategoryWords() {

		return (this.categoryWords);
	}

	public void setCategoryWords(CategoryWords categoryWords) {
		this.categoryWords = categoryWords;
	}

	@Override()
	public Object getKey() {

		return (this.recordPK);
	}

	@Override()
	public void setKey(Object key) {
		this.recordPK = (RecordPK) key;
	}

	@Override()
	@SuppressLint("SimpleDateFormat")
	public JSONObject packEntityToJsonObject(IJSONContext entityContext)
			throws JSONException {
		// yyyy-MM-dd'T'HH:mm:ssz
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Record.DATE, dateFormat.format(this.getDate()));
		jsonObject.put(Record.POINTS, Integer.toString(this.getPoints()));
		jsonObject.put(Record.RECORD_PK, this.getRecordPK()
				.packEntityToJsonObject(this.getRecordPK()));

		return (jsonObject);
	}

	@Override()
	@SuppressLint("SimpleDateFormat")
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {
		// yyyy-MM-dd'T'HH:mm:ssz
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.setCategoryWords(null);

		StringBuilder date = new StringBuilder(
				jsonObject.getString(Record.DATE));

		try {
			this.setDate(dateFormat.parse(date.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.setPlayer(null);
		this.setPoints(jsonObject.getInt(Record.POINTS));
		this.setRecordPK(new RecordPK(jsonObject
				.getJSONObject(Record.RECORD_PK)));

		return (this);
	}

	@Override()
	public int describeContents() {

		return (0);
	}

	@Override()
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(this.getDate());
		dest.writeInt(this.getPoints());
		dest.writeParcelable(this.getRecordPK(), 0);
	}

	@Override()
	public int hashCode() {
		int hash = 0;
		hash += (recordPK != null ? recordPK.hashCode() : 0);

		return (hash);
	}

	@Override()
	public boolean equals(Object object) {
		if (!(object instanceof Record)) {

			return (false);
		}

		Record other = (Record) object;
		if ((this.recordPK == null && other.recordPK != null)
				|| (this.recordPK != null && !this.recordPK
						.equals(other.recordPK))) {

			return (false);
		}

		return (true);
	}

	@Override()
	public String toString() {

		return ("co.edu.udea.os.ahorcado.persistence.entity.Record[ recordPK="
				+ this.recordPK + " ]");
	}

	public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {

		@Override()
		public Record createFromParcel(Parcel source) {

			return (new Record(source));
		}

		@Override()
		public Record[] newArray(int size) {

			return (new Record[size]);
		}
	};
}