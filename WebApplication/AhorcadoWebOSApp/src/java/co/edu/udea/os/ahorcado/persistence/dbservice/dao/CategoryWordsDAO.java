package co.edu.udea.os.ahorcado.persistence.dbservice.dao;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryWordsDAO;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWordsPK;
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
public class CategoryWordsDAO extends AbstractEntityDAO
        implements ICategoryWordsDAO {

    public CategoryWordsDAO() {
        super();
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public CategoryWords deleteCategoryWords(CategoryWords billboard) {

        return ((CategoryWords) super.delete(billboard));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<CategoryWords> findAllCategoriesWords() {

        return ((List<CategoryWords>) super.findAll(CategoryWords.class));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<CategoryWords> findCategoriesWordsByAttributes(
            Object... attributes) {

        return ((List<CategoryWords>) super.findByAttributes(
                CategoryWords.class, attributes));
    }

    @Override()
    public CategoryWords findCategoryWords(CategoryWordsPK key) {

        return ((CategoryWords) super.find(CategoryWords.class, key));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public CategoryWordsPK saveCategoryWords(CategoryWords billboard) {

        return ((CategoryWordsPK) super.save(billboard));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public CategoryWords updateCategoryWords(CategoryWords billboard) {

        return ((CategoryWords) super.update(billboard));
    }

    @Override()
    public long countCategoriesWords() {

        return (((Long) super.count(CategoryWords.class)).longValue());
    }

    @Override()
    public List<CategoryWords> executeNamedQueryForCategoriesWords(
            String namedQuery, String paramName, Object paramValue) {
        List<CategoryWords> categoryWordsFound = new ArrayList<>();

        for (Object o : super.executeNamedQuery(namedQuery, paramName,
                paramValue)) {
            categoryWordsFound.add((CategoryWords) o);
        }

        return (categoryWordsFound);
    }
}