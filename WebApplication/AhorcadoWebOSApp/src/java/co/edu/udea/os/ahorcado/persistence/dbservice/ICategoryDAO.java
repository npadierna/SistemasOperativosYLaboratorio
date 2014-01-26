package co.edu.udea.os.ahorcado.persistence.dbservice;

import co.edu.udea.os.ahorcado.persistence.entity.Category;
import java.util.List;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface ICategoryDAO {

    public Category deleteCategory(Category category);

    public List<Category> findAllCategories();

    public List<Category> findCategoriesByAttributes(Object... attributes);

    public Category findCategory(String key);

    public String saveCategory(Category category);

    public Category updateCategory(Category category);

    public long countCategories();

    public List<Category> executeNamedQueryForCategories(String namedQuery,
            String parameterName, Object parameterValue);
}