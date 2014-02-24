package co.edu.udea.os.ahorcado.service.webservice;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import co.edu.udea.os.ahorcado.persistence.entity.Record;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface IRecordWS {

	public List<Record> findAllRecordsForPlayer(String playerUserName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException;

	public List<Record> findBestRecordsForAllCategories()
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException;

	public Record findBestRecordForCategory(String categoryName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException;

	public Record findBestRecordForPlayerInCategory(String playerUserName,
			String categoryName) throws ClientProtocolException, IOException,
			JSONException, URISyntaxException;

	public Record saveRecord(Record record) throws ClientProtocolException,
			IOException, JSONException, URISyntaxException;
}