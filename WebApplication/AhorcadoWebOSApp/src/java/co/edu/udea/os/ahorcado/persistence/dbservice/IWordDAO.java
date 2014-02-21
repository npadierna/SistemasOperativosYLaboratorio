package co.edu.udea.os.ahorcado.persistence.dbservice;

import co.edu.udea.os.ahorcado.persistence.entity.Word;
import java.util.List;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface IWordDAO {

    public Word deleteWord(Word word);

    public List<Word> findAllWords();

    public List<Word> findWordsByAttributes(Object... attributes);

    public Word findWord(String key);

    public String saveWord(Word word);

    public Word updateWord(Word word);

    public long countWords();

    public List<Word> executeNamedQueryForWords(String namedQuery,
            String parameterName, Object parameterValue);
}