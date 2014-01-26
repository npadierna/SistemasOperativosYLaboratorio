package co.edu.udea.os.ahorcado.service.webservice;

import co.edu.udea.os.ahorcado.persistence.entity.Word;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@WebService()
public interface IWordWS {

    public List<Word> findAllWords();
}