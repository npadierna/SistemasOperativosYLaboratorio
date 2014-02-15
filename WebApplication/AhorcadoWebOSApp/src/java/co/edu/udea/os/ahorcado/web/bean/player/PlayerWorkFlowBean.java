package co.edu.udea.os.ahorcado.web.bean.player;

import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
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
public final class PlayerWorkFlowBean implements Serializable {

    private static final long serialVersionUID = 950548434313068544L;
    @Autowired()
    private IPlayerDAO playerDAO;
    private Player player;

    public PlayerWorkFlowBean() {
        super();

        this.setPlayer(new Player());
    }

    public Player getPlayer() {

        return (this.player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void savePlayer(ActionEvent actionEvent) {
    }
}