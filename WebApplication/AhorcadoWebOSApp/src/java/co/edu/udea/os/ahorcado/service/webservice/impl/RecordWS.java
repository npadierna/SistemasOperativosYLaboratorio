package co.edu.udea.os.ahorcado.service.webservice.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IRecordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;
import co.edu.udea.os.ahorcado.service.webservice.WebServiceContext;
import java.util.ArrayList;
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

//    @GET()
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public Record findRecordForCategoryWord(CategoryWords categoryWord) {

        return (null);
    }

//    @GET()
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public Record findBestRecordForCategory(@QueryParam("name") String categoryName) {

        return (null);
    }

    @GET()
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Override()
    public List<Record> findRecordsForAllCategories() {

        return (null);
    }
}