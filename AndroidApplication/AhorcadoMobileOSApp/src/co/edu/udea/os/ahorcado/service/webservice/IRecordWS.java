package co.edu.udea.os.ahorcado.service.webservice;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Record;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface IRecordWS {

    public List<Record> findAllRecordsForPlayer(String playerUserName);

    public Record findBestRecordForPlayerInCategory(String playerUserName,
            String categoryName);

    public boolean saveBestRecordForPlayer(Record record);

    public Record findBestRecordForCategory(String categoryName);

    public List<Record> findBestRecordsForAllCategories();
}