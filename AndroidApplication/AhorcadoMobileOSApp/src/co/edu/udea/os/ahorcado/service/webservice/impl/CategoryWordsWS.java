package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWordsWS;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWordsWS extends WebServiceContext implements
		ICategoryWordsWS {

	private static final String TAG = CategoryWordsWS.class.getSimpleName();

	public CategoryWordsWS(WebServiceServer webServiesServer) {
		super(webServiesServer);
	}

	@Override()
	public Word findOneWordForCategory(String categoryName) {

		return (null);
	}

	@Override()
	public List<Word> findAllWordsForCategory(String categoryName) {

		return (null);
	}
}