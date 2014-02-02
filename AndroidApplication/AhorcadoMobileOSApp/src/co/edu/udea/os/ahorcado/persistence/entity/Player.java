package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class Player implements IEntityContext, Serializable {

    private static final long serialVersionUID = 7756565228171404288L;
    private String userName;
    private String password;
    private String email;
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