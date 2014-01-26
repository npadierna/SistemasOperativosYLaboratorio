package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.webservice.IPlayerWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import javax.jws.WebService;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@Component()
@Path(WebServiceContext.PlayerWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.IPlayerWS")
public class PlayerWS implements IPlayerWS {

    @Autowired()
    private IPlayerDAO playerDAO;

    public PlayerWS() {
        super();
    }

    @Override()
    public Player findPlayerByLogin(String userName, String password) {

        return (null);
    }
}