package co.edu.udea.os.ahorcado.web.bean.word;

import co.edu.udea.os.ahorcado.persistence.dbservice.IWordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import java.io.Serializable;
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
public final class CategoryWordsWorkFlowBean implements Serializable {

    private static final long serialVersionUID = 2878162644554974208L;
    @Autowired()
    private IWordDAO wordDAO;
    private Category category;
    private Word word;

    public CategoryWordsWorkFlowBean() {
        super();

        this.setCategory(new Category());
        this.setWord(new Word());
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

    public void saveCategoryWords(ActionEvent actionEvent) {
    }
}