package co.edu.udea.os.ahorcado.persistence.entity;

import org.json.JSONException;
import org.json.JSONObject;

public interface IJSONContext {

	public JSONObject packEntityToJsonObject(IJSONContext entityContext);

	public IJSONContext unpackJsonOjectToEntity(JSONObject jsonObject)
			throws JSONException;
}