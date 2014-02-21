package co.edu.udea.os.ahorcado.persistence.dbservice.dao;

import co.edu.udea.os.ahorcado.persistence.dbservice.IRecordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.persistence.entity.RecordPK;
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
public class RecordDAO extends AbstractEntityDAO implements IRecordDAO {

    public RecordDAO() {
        super();
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Record deleteRecord(Record record) {

        return ((Record) super.delete(record));
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Record> findAllRecords() {

        return ((List<Record>) super.findAll(Record.class));
    }

    @Override()
    public List<Record> findAllRecordsForPlayer(Player player) {
        List<Record> records = null;

        if ((player != null) && (player.getKey() != null)) {
            records = this.findRecordsByAttributes("recordPK.userName",
                    player.getKey());
        }

        return (records);
    }

    @Override()
    public Record findBestRecordForPlayerInCategory(Player player,
            Category category) {
        if ((player != null) && (player.getKey() != null) && (category != null)
                && (category.getKey() != null)) {
            List<Record> records = this.findRecordsByAttributes(
                    "recordPK.userName", player.getKey(), "recordPK.category",
                    category.getKey());

            if ((records != null) && (!records.isEmpty())) {

                return (this.findHigherRecord(records));
            }
        }

        return (null);
    }

    @Override()
    public Record findBestRecordForCategory(Category category) {
        if ((category != null) && (category.getKey() != null)) {
            List<Record> records = this.findRecordsByAttributes(
                    "recordPK.category", category.getKey());

            if ((records != null) && (!records.isEmpty())) {

                return (this.findHigherRecord(records));
            }
        }

        return (null);
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<Record> findRecordsByAttributes(Object... attributes) {

        return ((List<Record>) super.findByAttributes(Record.class,
                attributes));
    }

    @Override()
    public Record findRecordForCategoryWords(CategoryWords categoryWords) {
        if ((categoryWords != null) && (categoryWords.getKey() != null)) {
            List<Record> records = this.findRecordsByAttributes("recordPK.category",
                    categoryWords.getCategoryWordsPK().getCategory(),
                    "recordPK.word",
                    categoryWords.getCategoryWordsPK().getWord());

            if ((records != null) && (!records.isEmpty())) {

                return (records.get(0));
            }
        }

        return (null);
    }

    @Override()
    public Record findRecord(RecordPK key) {

        return ((Record) super.find(Record.class, key));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public RecordPK saveRecord(Record record) {

        return ((RecordPK) super.save(record));
    }

    @Override()
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Record updateRecord(Record record) {

        return ((Record) super.update(record));
    }

    @Override()
    public long countRecords() {

        return (((Long) super.count(Record.class)).longValue());
    }

    @Override()
    public List<Record> executeNamedQueryForRecords(String namedQuery,
            String paramName, Object paramValue) {
        List<Record> recordsFound = new ArrayList<>();

        for (Object o : super.executeNamedQuery(namedQuery, paramName,
                paramValue)) {
            recordsFound.add((Record) o);
        }

        return (recordsFound);
    }

    private Record findHigherRecord(List<Record> records) {
        Record record = records.get(0);

        for (int index = 1; index < records.size(); index++) {
            Record r = records.get(index);

            if ((record.getPoints() < r.getPoints())
                    || ((record.getPoints() == r.getPoints())
                    && (record.getDate().before(r.getDate())))) {
                record = r;
            }
        }

        return (record);
    }
}