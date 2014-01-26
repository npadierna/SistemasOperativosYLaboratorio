package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.Path;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Component()
@Path(WebServiceContext.RecordWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.IRecordWS")
public class RecordWS implements IRecordWS {

    public RecordWS() {
        super();
    }

    @Override()
    public List<Record> findRecordsByPlayer(Player player) {

        return (null);
    }
}