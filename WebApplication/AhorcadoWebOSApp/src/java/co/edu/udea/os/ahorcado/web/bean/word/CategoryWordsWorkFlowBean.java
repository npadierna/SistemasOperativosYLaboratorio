package co.edu.udea.os.ahorcado.web.bean.word;

import co.edu.udea.os.ahorcado.domain.util.Util;
import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryWordsDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IWordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWordsPK;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Component()
@SessionScoped()
public final class CategoryWordsWorkFlowBean implements Serializable {

    private static final long serialVersionUID = 2878162644554974208L;
    @Autowired()
    private ICategoryWordsDAO categoryWordsDAO;
    @Autowired()
    private IWordDAO wordDAO;
    @Autowired()
    private ICategoryDAO categoryDAO;
    private Category category;
    private Word word;
    private List<String> categoriesNames;

    public CategoryWordsWorkFlowBean() {
        super();

        this.setCategory(new Category());
        this.setWord(new Word());
        this.setCategoriesNames(new ArrayList<String>());
    }

    public Category getCategory() {

        return (this.category);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Word getWord() {

        return (this.word);
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public List<String> getCategoriesNames() {
        return categoriesNames;
    }

    public void setCategoriesNames(List<String> categoriesNames) {
        this.categoriesNames = categoriesNames;
    }

    public void saveCategoryWords(ActionEvent actionEvent) {
        boolean correct = true;
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Datos Inválidos",
                "Por favor verifique sus datos para registrarse.");

        if (this.word.getName() == null
                || this.word.getName().trim().equals("")
                || this.word.getName().contains(" ")
                || this.word.getName().contains(";")
                || this.word.getName().contains(".")
                || this.word.getName().contains(",")
                || this.word.getName().contains("-")
                || this.word.getName().contains("_")
                || this.word.getName().contains("´")) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Error: Datos Inválidos",
                    "Por favor ingrese una palabra correcta.");

            correct = false;
        }

        if (correct) {
            this.word.setName(Util.convertName(this.word.getName()));
            if (wordDAO.findWord(this.word.getName()) == null) {
                if (this.wordDAO.saveWord(this.word) == null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Inténtelo de Nuevo",
                            "Ha ocurrido un error en el guardado. Inténtelo de nuevo.");

                    correct = false;
                }
            }

            if (correct) {
                CategoryWordsPK categoryWordsPK = new CategoryWordsPK(this.category.getName(),
                        this.word.getName());
                CategoryWords categoryWords = this.categoryWordsDAO.findCategoryWords(categoryWordsPK);
                if (categoryWords != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error: Palabra Existente",
                            "La palabra ingresada ya existe. Por favor cámbiela.");
                } else {
                    categoryWords = new CategoryWords(categoryWordsPK);
                    CategoryWordsPK cwpk = this.categoryWordsDAO.
                            saveCategoryWords(categoryWords);

                    if (cwpk == null) {
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Inténtelo de Nuevo",
                                "Ha ocurrido un error en el guardado. Inténtelo de nuevo.");
                        correct = false;
                    } else {
                        this.setWord(new Word());
                        this.setCategory(new Category());
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Guardado existoso",
                                "La nueva palabra ha sido guardada satisfactoriamente.");
                    }
                }
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam(Util.CORRECT_OPERATION, correct);
    }

    @PostConstruct()
    private void findCategoriesNames() {
        List<Category> categories = this.categoryDAO.findAllCategories();

        for (Category c : categories) {
            this.getCategoriesNames().add(c.getName());
        }
    }
}
