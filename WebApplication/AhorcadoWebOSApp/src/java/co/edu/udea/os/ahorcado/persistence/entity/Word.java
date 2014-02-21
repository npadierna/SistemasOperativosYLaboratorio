package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
@Entity()
@NamedQueries({
    @NamedQuery(name = "Word.findAll", query = "SELECT w FROM Word w"),
    @NamedQuery(name = "Word.findByName", query = "SELECT w FROM Word w WHERE w.name = :name")})
@Table(name = "WORD")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement()
public class Word implements IEntityContext, Serializable {

    private static final long serialVersionUID = 4061688705156592640L;
    @Id()
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 15)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "word1")
    private List<CategoryWords> categoryWordsList;

    public Word() {
        super();
    }

    public Word(String name) {
        this.name = name;
    }

    public String getName() {

        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient()
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
}