package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWordsPK implements IJSONContext, Serializable {

	private static final long serialVersionUID = 8693019297638724608L;

	private String category;
	private String word;

	public CategoryWordsPK() {
		super();
	}

	public CategoryWordsPK(String category, String word) {
		this.category = category;
		this.word = word;
	}

	public CategoryWordsPK(JSONObject jsonObject) throws JSONException {
		this.unpackJsonOjectToEntity(jsonObject);
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
	public JSONObject packEntityToJsonObject(IJSONContext entityContext) {

		return (null);
	}

	@Override()
	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException {

		return (this);
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
		if (!(object instanceof CategoryWordsPK)) {

			return (false);
		}

		CategoryWordsPK other = (CategoryWordsPK) object;
		if ((this.category == null && other.category != null)
				|| (this.category != null && !this.category
						.equals(other.category))) {

			return (false);
		}

		if ((this.word == null && other.word != null)
				|| (this.word != null && !this.word.equals(other.word))) {

			return (false);
		}

		return (true);
	}

	@Override()
	public String toString() {

		return ("co.edu.udea.os.ahorcado.persistence.entity.CategoryWordsPK[ category="
				+ this.category + ", word=" + this.word + " ]");
	}
}