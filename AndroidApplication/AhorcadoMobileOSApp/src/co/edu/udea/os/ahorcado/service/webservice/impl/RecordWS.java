package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.service.webservice.IRecordWS;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class RecordWS implements IRecordWS {

	public RecordWS() {
        super();
    }

    @Override()
    public List<Record> findAllRecordsForPlayer(String playerUserName) {

        return (null);
    }

    @Override()
    public Record findBestRecordForPlayerInCategory(String playerUserName, String categoryName) {

        return (null);
    }

    @Override()
    public boolean saveBestRecordForPlayer(Record record) {

    	return (false);
    }

    @Override()
    public Record findBestRecordForCategory(String categoryName) {

        return (null);
    }

    @Override()
    public List<Record> findBestRecordsForAllCategories() {

        return (null);
    }
}