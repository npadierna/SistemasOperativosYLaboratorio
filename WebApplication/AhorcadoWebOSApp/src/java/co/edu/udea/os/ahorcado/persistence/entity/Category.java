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
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")})
@Table(name = "CATEGORY")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement()
public class Category implements IEntityContext, Serializable {

    private static final long serialVersionUID = 3484225594024590336L;
    @Id()
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 25)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "image_name")
    private String imageName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category1")
    private List<CategoryWords> categoryWordsList;

    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    public String getName() {

        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {

        return (this.imageName);
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
        if (!(object instanceof Category)) {

            return (false);
        }

        Category other = (Category) object;
        if ((this.name == null && other.name != null) || (this.name != null
                && !this.name.equals(other.name))) {

            return (false);
        }

        return (true);
    }

    @Override()
    public String toString() {

        return ("co.edu.udea.os.ahorcado.persistence.entity.Category[ name="
                + this.name + " ]");
    }
}
