package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Entity()
@NamedQueries({
    @NamedQuery(name = "CategoryWords.findAll", query = "SELECT c FROM CategoryWords c"),
    @NamedQuery(name = "CategoryWords.findByCategory", query = "SELECT c FROM CategoryWords c WHERE c.categoryWordsPK.category = :category"),
    @NamedQuery(name = "CategoryWords.findByWord", query = "SELECT c FROM CategoryWords c WHERE c.categoryWordsPK.word = :word")})
@Table(name = "CATEGORY_WORDS")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement()
public class CategoryWords implements IEntityContext, Serializable {

    private static final long serialVersionUID = 3074146297965430784L;
    @EmbeddedId()
    protected CategoryWordsPK categoryWordsPK;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "categoryWords")
    private Record record;
    @JoinColumn(name = "word", referencedColumnName = "name",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Word word1;
    @JoinColumn(name = "category", referencedColumnName = "name",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
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

    public CategoryWordsPK getCategoryWordsPK() {

        return (this.categoryWordsPK);
    }

    public void setCategoryWordsPK(CategoryWordsPK categoryWordsPK) {
        this.categoryWordsPK = categoryWordsPK;
    }

    public Record getRecord() {

        return (this.record);
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @XmlTransient()
    public Word getWord1() {

        return (this.word1);
    }

    public void setWord1(Word word1) {
        this.word1 = word1;
    }

    @XmlTransient()
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
                || (this.categoryWordsPK != null
                && !this.categoryWordsPK.equals(other.categoryWordsPK))) {

            return (false);
        }

        return (true);
    }

    @Override()
    public String toString() {

        return ("co.edu.udea.os.ahorcado.persistence.entity.CategoryWords[ categoryWordsPK="
                + this.categoryWordsPK + " ]");
    }
}