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
public interface ICategoryWordsWS {

    public Word findOneWordForCategory(String categoryName);

    public List<Word> findAllWordsForCategory(String categoryName);
}