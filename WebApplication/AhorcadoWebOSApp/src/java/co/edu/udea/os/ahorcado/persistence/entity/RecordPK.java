package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Embeddable()
public class RecordPK implements Serializable {

    private static final long serialVersionUID = 1975712992744627200L;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 25)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 15)
    @Column(name = "word")
    private String word;

    public RecordPK() {
        super();
    }

    public RecordPK(String category, String word) {
        this.category = category;
        this.word = word;
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
    public int hashCode() {
        int hash = 0;
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
        if ((this.category == null && other.category != null)
                || (this.category != null
                && !this.category.equals(other.category))) {

            return (false);
        }

        if ((this.word == null && other.word != null) || (this.word != null
                && !this.word.equals(other.word))) {

            return (false);
        }

        return (true);
    }

    @Override()
    public String toString() {

        return ("co.edu.udea.os.ahorcado.persistence.entity.RecordPK[ category="
                + this.category + ", word=" + this.word + " ]");
    }
}