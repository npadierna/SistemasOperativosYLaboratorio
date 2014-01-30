package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryWordsDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWordsWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Component()
@Path(WebServiceContext.CategoryWordsWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.ICategoryWordsWS")
public class CategoryWordsWS implements ICategoryWordsWS {

    @Autowired()
    private ICategoryDAO categoryDAO;
    @Autowired()
    private ICategoryWordsDAO categoryWordsDAO;

    public CategoryWordsWS() {
        super();
    }

//    @GET()
//    @Path("")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public Word findOneWordForCategory(@QueryParam("categoryname") String categoryName) {

        return (null);
    }

    @GET()
    @Path(WebServiceContext.CategoryWordsWSContext.CATEGORY_ALL_WORDS_PATH)
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public List<Word> findAllWordsForCategory(@QueryParam("categoryname") String categoryName) {
        Category category = this.categoryDAO.findCategory(categoryName);

        if (category != null) {
            List<CategoryWords> categoriesWords =
                    this.categoryWordsDAO.findAllCategoriesWordsForCategory(category);

            if ((categoriesWords != null) && (!categoriesWords.isEmpty())) {

                return (this.createWordsFromCategoryWords(categoriesWords));
            }
        }

        return (null);
    }

    private List<Word> createWordsFromCategoryWords(
            List<CategoryWords> categoriesWords) {
        if ((categoriesWords == null) || (categoriesWords.isEmpty())) {

            return (null);
        }

        List<Word> words = new ArrayList<>();
        for (CategoryWords categoryWords : categoriesWords) {
            words.add(new Word(categoryWords.getCategoryWordsPK().getWord()));
        }

        return (words);
    }
}