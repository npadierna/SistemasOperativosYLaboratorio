package co.edu.udea.os.ahorcado.persistence.entity;

import org.json.JSONObject;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface IEntityContext {

	public Object getKey();

	public void setKey(Object key);

	public JSONObject packEntityToJsonObject(IEntityContext entityContext);

	public IEntityContext unpackJsonOjectToEntity(JSONObject jsonObject);
}