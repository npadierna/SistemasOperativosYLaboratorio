package co.edu.udea.os.ahorcado.web.bean.category;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Component()
@SessionScoped()
public final class CategoryWorkFlowBean implements Serializable {

    private static final long serialVersionUID = 280914023041822720L;
    @Autowired()
    private ICategoryDAO categoryDAO;
    private Category category;
    private List<String> categoriesNames;

    public CategoryWorkFlowBean() {
        super();

        this.setCategory(new Category());
        this.setCategoriesNames(new ArrayList<String>());
    }

    public Category getCategory() {

        return (this.category);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getCategoriesNames() {

        return (this.categoriesNames);
    }

    public void setCategoriesNames(List<String> categoriesNames) {
        this.categoriesNames = categoriesNames;
    }

    public void saveCategory(ActionEvent actionEvent) {
    }

    @PostConstruct()
    private void findCategoriesNames() {
       List<Category> categories = this.categoryDAO.findAllCategories();

        for (Category c : categories) {
            this.getCategoriesNames().add(c.getName());
        }
    }
}