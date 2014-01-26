package co.edu.udea.os.ahorcado.service.webservice;

import co.edu.udea.os.ahorcado.persistence.entity.Player;
import javax.jws.WebService;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@WebService()
public interface IPlayerWS {

    public Player findPlayerByLogin(String userName, String password);
}