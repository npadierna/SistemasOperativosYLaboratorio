package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class Category implements IEntityContext, IJSONContext, Serializable {

	private static final long serialVersionUID = 3484225594024590336L;

	private static final String NAME = "name";

	private String name;
	private List<CategoryWords> categoryWordsList;

	public Category() {
		super();
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
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
	public JSONObject packEntityToJsonObject(IJSONContext entityContext)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(Category.NAME, this.getName());

		return (jsonObject);
	}

	@Override()
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {
		this.setCategoryWordsList(null);
		this.setName(jsonObject.getString(Category.NAME));

		return (this);
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
		if ((this.name == null && other.name != null)
				|| (this.name != null && !this.name.equals(other.name))) {

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