package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Entity()
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findByUserName", query = "SELECT r FROM Record r WHERE r.recordPK.userName = :userName"),
    @NamedQuery(name = "Record.findByCategory", query = "SELECT r FROM Record r WHERE r.recordPK.category = :category"),
    @NamedQuery(name = "Record.findByWord", query = "SELECT r FROM Record r WHERE r.recordPK.word = :word"),
    @NamedQuery(name = "Record.findByPoints", query = "SELECT r FROM Record r WHERE r.points = :points"),
    @NamedQuery(name = "Record.findByDate", query = "SELECT r FROM Record r WHERE r.date = :date")})
@Table(name = "RECORD")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Record implements IEntityContext, Serializable {

    private static final long serialVersionUID = 9084931501438312448L;
    @EmbeddedId()
    protected RecordPK recordPK;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "points")
    private int points;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumns({
        @JoinColumn(name = "category", referencedColumnName = "category",
            insertable = false, updatable = false),
        @JoinColumn(name = "word", referencedColumnName = "word",
            insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CategoryWords categoryWords;
    @JoinColumn(name = "user_name", referencedColumnName = "user_name",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
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
