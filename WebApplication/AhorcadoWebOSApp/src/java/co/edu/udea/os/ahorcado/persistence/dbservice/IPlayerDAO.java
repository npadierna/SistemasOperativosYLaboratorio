package co.edu.udea.os.ahorcado.persistence.dbservice;

import co.edu.udea.os.ahorcado.persistence.entity.Player;
import java.util.List;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface IPlayerDAO {

    public Player deletePlayer(Player player);

    public List<Player> findAllPlayers();

    public List<Player> findPlayersByAttributes(Object... attributes);

    public Player findPlayer(String key);

    public String savePlayer(Player player);

    public Player updatePlayer(Player player);

    public long countPlayers();

    public List<Player> executeNamedQueryForPlayers(String namedQuery,
            String parameterName, Object parameterValue);
}