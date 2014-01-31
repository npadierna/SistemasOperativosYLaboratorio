package co.edu.udea.os.ahorcado.service.webservice;

import co.edu.udea.os.ahorcado.persistence.entity.Record;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
@WebService()
public interface IRecordWS {

    public List<Record> findAllRecordsForPlayer(String playerUserName);

    public Record findBestRecordForPlayerInCategory(String playerUserName,
            String categoryName);

    public Record findBestRecordForCategory(String categoryName);

    public List<Record> findBestRecordsForAllCategories();
}