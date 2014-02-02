package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Component()
@Path(WebServicePath.CategoryWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.ICategoryWS")
public class CategoryWS implements ICategoryWS {

    @Autowired()
    private ICategoryDAO categoryDAO;

    public CategoryWS() {
        super();
    }

    @GET()
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public List<Category> findAllCategories() {
        List<Category> categories = this.categoryDAO.findAllCategories();

        if ((categories != null) && (!categories.isEmpty())) {

            return (categories);
        }

        return (null);
    }
}