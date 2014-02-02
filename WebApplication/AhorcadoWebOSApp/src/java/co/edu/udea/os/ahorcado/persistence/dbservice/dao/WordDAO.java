package co.edu.udea.os.ahorcado.persistence.dbservice.dao;

import co.edu.udea.os.ahorcado.persistence.dbservice.IWordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Repository()
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class WordDAO extends AbstractEntityDAO implements IWordDAO {

    public WordDAO() {
        super();
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Word deleteWord(Word billboard) {

        return ((Word) super.delete(billboard));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Word> findAllWords() {

        return ((List<Word>) super.findAll(Word.class));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Word> findWordsByAttributes(Object... attributes) {

        return ((List<Word>) super.findByAttributes(Word.class,
                attributes));
    }

    @Override()
    public Word findWord(String key) {

        return ((Word) super.find(Word.class, key));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public String saveWord(Word billboard) {

        return ((String) super.save(billboard));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Word updateWord(Word billboard) {

        return ((Word) super.update(billboard));
    }

    @Override()
    public long countWords() {

        return (((Long) super.count(Word.class)).longValue());
    }

    @Override()
    public List<Word> executeNamedQueryForWords(String namedQuery,
            String paramName, Object paramValue) {
        List<Word> wordsFound = new ArrayList<>();

        for (Object o : super.executeNamedQuery(namedQuery, paramName,
                paramValue)) {
            wordsFound.add((Word) o);
        }

        return (wordsFound);
    }
}