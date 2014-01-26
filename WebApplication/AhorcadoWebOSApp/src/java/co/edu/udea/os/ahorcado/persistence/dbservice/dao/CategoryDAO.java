package co.edu.udea.os.ahorcado.persistence.dbservice.dao;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
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
public class CategoryDAO extends AbstractEntityDAO implements ICategoryDAO {

    public CategoryDAO() {
        super();
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Category deleteCategory(Category billboard) {

        return ((Category) super.delete(billboard));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Category> findAllCategories() {

        return ((List<Category>) super.findAll(Category.class));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Category> findCategoriesByAttributes(Object... attributes) {

        return ((List<Category>) super.findByAttributes(Category.class,
                attributes));
    }

    @Override()
    public Category findCategory(String key) {

        return ((Category) super.find(Category.class, key));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public String saveCategory(Category billboard) {

        return ((String) super.save(billboard));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Category updateCategory(Category billboard) {

        return ((Category) super.update(billboard));
    }

    @Override()
    public long countCategories() {

        return (((Long) super.count(Category.class)).longValue());
    }

    @Override()
    public List<Category> executeNamedQueryForCategories(String namedQuery,
            String paramName, Object paramValue) {
        List<Category> categoriesFound = new ArrayList<>();

        for (Object o : super.executeNamedQuery(namedQuery, paramName,
                paramValue)) {
            categoriesFound.add((Category) o);
        }

        return (categoriesFound);
    }
}