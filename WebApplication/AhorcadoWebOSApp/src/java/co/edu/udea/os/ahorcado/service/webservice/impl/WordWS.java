package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.IWordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.webservice.IWordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import java.util.ArrayList;
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
@Path(WebServiceContext.WordWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.IWordWS")
public class WordWS implements IWordWS {

    @Autowired()
    private IWordDAO wordDAO;

    public WordWS() {
        super();
    }

    @GET()
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public List<Word> findAllWords() {
        List<Word> words = this.wordDAO.findAllWords();

        if ((words != null) && (!words.isEmpty())) {

            return (words);
        }

        return (null);
    }
}