package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.service.webservice.IPlayerWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServicePath;
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
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
@Component()
@Path(WebServicePath.PlayerWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.IPlayerWS")
public class PlayerWS implements IPlayerWS {

    @Autowired()
    private IPlayerDAO playerDAO;

    public PlayerWS() {
        super();
    }

    @GET()
    @Path(WebServicePath.PlayerWSContext.PLAYER_LOGIN_PATH)
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public Player findPlayerByLogin(@QueryParam("username") String userName,
            @QueryParam("password") String password) {

        return (this.playerDAO.findPlayerByLogin(userName, password));
    }
}