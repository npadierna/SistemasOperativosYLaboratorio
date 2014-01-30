package co.edu.udea.os.ahorcado.service.webservice;

import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
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

    public Record findRecordForCategoryWord(CategoryWords categoryWord);

    public Record findBestRecordForCategory(String categoryName);

    public List<Record> findRecordsForAllCategories();
}