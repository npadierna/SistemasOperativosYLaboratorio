package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class Word implements IEntityContext, Serializable {

    private static final long serialVersionUID = 4061688705156592640L;
    private String name;
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