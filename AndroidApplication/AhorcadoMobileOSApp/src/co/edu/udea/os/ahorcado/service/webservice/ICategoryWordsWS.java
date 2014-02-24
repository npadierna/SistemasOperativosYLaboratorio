package co.edu.udea.os.ahorcado.service.webservice;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface ICategoryWordsWS {

	public List<CategoryWords> findAllWordsForCategory(String categoryName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException;

	public CategoryWords findOneWordForCategory(String categoryName)
			throws ClientProtocolException, IOException, JSONException,
			URISyntaxException;
}