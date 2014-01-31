package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IRecordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import java.util.List;
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
 */
@Component()
@Path(WebServiceContext.RecordWSContext.ROOT_PATH)
@WebService(endpointInterface = "co.edu.udea.os.ahorcado.service.webservice.IRecordWS")
public class RecordWS implements IRecordWS {

    @Autowired()
    private ICategoryDAO categoryDAO;
    @Autowired()
    private IPlayerDAO playerDAO;
    @Autowired()
    private IRecordDAO recordDAO;

    public RecordWS() {
        super();
    }

    @GET()
    @Path(WebServiceContext.RecordWSContext.PLAYER_ALL_RECORDS_PATH)
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public List<Record> findAllRecordsForPlayer(@QueryParam("username") String playerUserName) {
        Player player = this.playerDAO.findPlayer(playerUserName);

        if (player != null) {
            List<Record> recordsForPlayer =
                    this.recordDAO.findAllRecordsForPlayer(player);

            if ((recordsForPlayer != null) && (!recordsForPlayer.isEmpty())) {

                return (recordsForPlayer);
            }
        }

        return (null);
    }

    @GET()
    @Path(WebServiceContext.RecordWSContext.PLAYER_BEST_RECORD_FOR_CATEGORY_PATH)
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public Record findBestRecordForPlayerInCategory(@QueryParam("username") String playerUserName,
            @QueryParam("categoryname") String categoryName) {
        Category category = this.categoryDAO.findCategory(categoryName);
        Player player = this.playerDAO.findPlayer(categoryName);

        if ((category != null) && (player != null)) {

            return (this.recordDAO.findBestRecordForPlayerInCategory(player,
                    category));
        }

        return (null);
    }

    @GET()
    @Path(WebServiceContext.RecordWSContext.CATEGORY_BEST_RECORD_PATH)
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public Record findBestRecordForCategory(@QueryParam("name") String categoryName) {

        return (null);
    }

    @GET()
    @Path(WebServiceContext.RecordWSContext.CATEGORY_ALL_BEST_RECORDS_PATH)
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public List<Record> findBestRecordsForAllCategories() {

        return (null);
    }
}