package co.edu.udea.os.ahorcado.persistence.dbservice;

import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.persistence.entity.RecordPK;
import java.util.List;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface IRecordDAO {

    public Record deleteRecord(Record record);

    public List<Record> findAllRecords();

    public List<Record> findAllRecordsForPlayer(Player player);

    public Record findBestRecordForPlayerInCategory(Player player,
            Category category);

    public Record findBestRecordForCategory(Category category);

    public List<Record> findRecordsByAttributes(Object... attributes);

    public Record findRecord(RecordPK key);

    public RecordPK saveRecord(Record record);

    public Record updateRecord(Record record);

    public long countRecords();

    public List<Record> executeNamedQueryForRecords(String namedQuery,
            String parameterName, Object parameterValue);
}