package co.edu.udea.os.ahorcado.persistence.entity;

import java.io.Serializable;

import org.json.JSONObject;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWords implements IEntityContext, Serializable {

    private static final long serialVersionUID = 3074146297965430784L;
    protected CategoryWordsPK categoryWordsPK;
    private Record record;
    private Word word1;
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

    public Word getWord1() {

        return (this.word1);
    }

    public void setWord1(Word word1) {
        this.word1 = word1;
    }

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