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
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByUserName", query = "SELECT p FROM Player p WHERE p.userName = :userName"),
    @NamedQuery(name = "Player.findByPassword", query = "SELECT p FROM Player p WHERE p.password = :password"),
    @NamedQuery(name = "Player.findByEmail", query = "SELECT p FROM Player p WHERE p.email = :email")})
@Table(name = "PLAYER")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement()
public class Player implements IEntityContext, Serializable {

    private static final long serialVersionUID = 7756565228171404288L;
    @Id()
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 35)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 35)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<Record> recordList;

    public Player() {
        super();
    }

    public Player(String userName) {
        this.userName = userName;
    }

    public Player(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {

        return (this.userName);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {

        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return (this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient()
    public List<Record> getRecordList() {

        return (this.recordList);
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override()
    public Object getKey() {

        return (this.userName);
    }

    @Override()
    public void setKey(Object key) {
        this.userName = (String) key;
    }

    @Override()
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);

        return (hash);
    }

    @Override()
    public boolean equals(Object object) {
        if (!(object instanceof Player)) {

            return (false);
        }

        Player other = (Player) object;
        if ((this.userName == null && other.userName != null)
                || (this.userName != null
                && !this.userName.equals(other.userName))) {

            return (false);
        }

        return (true);
    }

    @Override()
    public String toString() {

        return ("co.edu.udea.os.ahorcado.persistence.entity.Player[ userName="
                + this.userName + " ]");
    }
}