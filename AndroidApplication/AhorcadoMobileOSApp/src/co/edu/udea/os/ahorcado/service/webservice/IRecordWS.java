package co.edu.udea.os.ahorcado.service.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import co.edu.udea.os.ahorcado.persistence.entity.Record;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface IRecordWS {

	public List<Record> findAllRecordsForPlayer(String playerUserName)
			throws URISyntaxException, ClientProtocolException, IOException,
			JSONException;

	public Record findBestRecordForPlayerInCategory(String playerUserName,
			String categoryName) throws URISyntaxException,
			ClientProtocolException, IOException, JSONException;

	public Record saveBestRecordForPlayer(Record record)
			throws URISyntaxException, JSONException,
			UnsupportedEncodingException, ClientProtocolException, IOException;

	public Record findBestRecordForCategory(String categoryName)
			throws URISyntaxException, ClientProtocolException, IOException,
			JSONException;

	public List<Record> findBestRecordsForAllCategories()
			throws URISyntaxException, ClientProtocolException, IOException,
			JSONException;
}