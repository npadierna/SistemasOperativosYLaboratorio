package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class Record implements IEntityContext, Serializable {

    private static final long serialVersionUID = 9084931501438312448L;
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
	public JSONObject packEntityToJsonObject(IEntityContext entityContext) {

		return (null);
	}

	@Override()
	public IEntityContext unpackJsonOjectToEntity(JSONObject jsonObject) {

		return (null);
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
                || (this.recordPK != null
                && !this.recordPK.equals(other.recordPK))) {

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
