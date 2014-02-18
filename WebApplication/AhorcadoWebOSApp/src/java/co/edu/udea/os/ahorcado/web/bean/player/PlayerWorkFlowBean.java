package co.edu.udea.os.ahorcado.web.bean.player;

import co.edu.udea.os.ahorcado.domain.util.ConvertUtillity;
import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
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
        boolean correct = true;
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Datos Inválidos",
                "Por favor verifique sus datos para registrarse.");

        if ((this.player.getEmail() == null)
                || (this.player.getEmail().trim().equals(""))
                || (this.player.getUserName() == null)
                || (this.player.getUserName().trim().equals(""))
                || (this.player.getPassword() == null)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Error: Datos Inválidos",
                    "Por favor ingrese los datos correctamente.");

            correct = false;
        }

        if (correct) {
            this.player.setEmail(this.player.getEmail().trim());
            this.player.setUserName(this.player.getUserName().trim());
            List<Player> players = this.playerDAO.findPlayersByAttributes(
                    "userName", this.player.getUserName());

            if (players.isEmpty()) {
                String response = this.playerDAO.savePlayer(this.player);
                if (response == null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Error",
                            "No se ha logrado realizar el registro satisfactoriamente."
                            + " Inténtelo de nuevo");

                    correct = false;
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Guardado",
                            "La operación se ha realizado exitosamente.");

                    this.setPlayer(new Player());
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Jugador existente",
                        "Ya existe un jugador con el mismo nombre de usuario."
                        + " Por favor verifique sus datos.");

                correct = false;
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam(ConvertUtillity.CORRECT_OPERATION, correct);
    }
}