package co.edu.udea.os.ahorcado.persistence.dbservice.dao;

import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
@Repository()
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class PlayerDAO extends AbstractEntityDAO implements IPlayerDAO {

    public PlayerDAO() {
        super();
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Player deletePlayer(Player player) {

        return ((Player) super.delete(player));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Player> findAllPlayers() {

        return ((List<Player>) super.findAll(Player.class));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Player> findPlayersByAttributes(Object... attributes) {

        return ((List<Player>) super.findByAttributes(Player.class,
                attributes));
    }

    @Override()
    public Player findPlayer(String key) {

        return ((Player) super.find(Player.class, key));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public Player findPlayerByLogin(String userName, String password) {
        List<Player> foundPlayers = this.findPlayersByAttributes("userName",
                userName, "password", password);

        if ((foundPlayers != null) && !(foundPlayers.isEmpty())) {

            return (foundPlayers.get(0));
        }

        return (null);
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public String savePlayer(Player player) {

        return ((String) super.save(player));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Player updatePlayer(Player player) {

        return ((Player) super.update(player));
    }

    @Override()
    public long countPlayers() {

        return (((Long) super.count(Player.class)).longValue());
    }

    @Override()
    public List<Player> executeNamedQueryForPlayers(String namedQuery,
            String paramName, Object paramValue) {
        List<Player> playersFound = new ArrayList<>();

        for (Object o : super.executeNamedQuery(namedQuery, paramName,
                paramValue)) {
            playersFound.add((Player) o);
        }

        return (playersFound);
    }
}