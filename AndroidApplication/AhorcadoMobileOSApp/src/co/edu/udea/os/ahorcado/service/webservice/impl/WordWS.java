package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.IWordWS;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class WordWS extends WebServiceContext implements IWordWS {

	private static final String TAG = WordWS.class.getSimpleName();

	public WordWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Word> findAllWords() {

		return (null);
	}
}