package co.edu.udea.os.ahorcado.persistence.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface IJSONContext {

	public JSONObject packEntityToJsonObject(IJSONContext entityContext)
			throws JSONException;

	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException;
}