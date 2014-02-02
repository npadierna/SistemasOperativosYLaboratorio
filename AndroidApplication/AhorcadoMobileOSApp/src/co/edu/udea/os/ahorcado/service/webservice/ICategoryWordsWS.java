package co.edu.udea.os.ahorcado.service.webservice;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Word;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface ICategoryWordsWS {

    public Word findOneWordForCategory(String categoryName);

    public List<Word> findAllWordsForCategory(String categoryName);
}