package co.edu.udea.os.ahorcado.persistence.dbservice;

import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWordsPK;
import java.util.List;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface ICategoryWordsDAO {

    public CategoryWords deleteCategoryWords(CategoryWords categoryWords);

    public List<CategoryWords> findAllCategoriesWords();

    public List<CategoryWords> findAllCategoriesWordsForCategory(
            Category category);

    public List<CategoryWords> findCategoriesWordsByAttributes(
            Object... attributes);

    public CategoryWords findCategoryWords(CategoryWordsPK key);

    public CategoryWordsPK saveCategoryWords(CategoryWords categoryWords);

    public CategoryWords updateCategoryWords(CategoryWords categoryWords);

    public long countCategoriesWords();

    public List<CategoryWords> executeNamedQueryForCategoriesWords(
            String namedQuery, String parameterName, Object parameterValue);
}