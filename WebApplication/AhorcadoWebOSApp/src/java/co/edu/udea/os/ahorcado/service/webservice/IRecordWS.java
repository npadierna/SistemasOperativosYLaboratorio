package co.edu.udea.os.ahorcado.service.webservice;

import co.edu.udea.os.ahorcado.persistence.entity.Record;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
@WebService()
public interface IRecordWS {

    public List<Record> findAllRecordsForPlayer(String playerUserName);

    public Record findBestRecordForPlayerInCategory(String playerUserName,
            String categoryName);

    public Response saveRecord(Record record);

    public Record findBestRecordForCategory(String categoryName);

    public List<Record> findBestRecordsForAllCategories();
}