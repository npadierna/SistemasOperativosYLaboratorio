package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class Record implements IEntityContext, IJSONContext, Serializable {

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.setCategoryWords(null);

		StringBuilder date = new StringBuilder(
				jsonObject.getString(Record.DATE));
		date.delete(date.indexOf("T"), date.length());

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

	// @Override()
	// public int describeContents() {
	//
	// return (0);
	// }
	//
	// @Override()
	// public void writeToParcel(Parcel dest, int flags) {
	// dest.writeInt(this.getPoints());
	// dest.writeValue(this.getDate());
	// }

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
}