package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWordsWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import javax.jws.WebService;
import javax.ws.rs.Path;
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

    public CategoryWordsWS() {
        super();
    }

    @Override()
    public Word findWordByCategoryName(String categoryName) {

        return (null);
    }
}