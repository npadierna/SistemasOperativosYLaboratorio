package co.edu.udea.os.ahorcado.persistence.dbservice;

import co.edu.udea.os.ahorcado.persistence.entity.IEntityContext;
import java.util.List;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface IEntityDAO {

    public IEntityContext delete(IEntityContext entity);

    @SuppressWarnings("rawtypes")
    public Object findAll(Class clazz);

    @SuppressWarnings("rawtypes")
    public Object findByAttributes(Class clazz, Object... attributes);

    @SuppressWarnings("rawtypes")
    public IEntityContext find(Class clazz, Object key);

    public Object save(IEntityContext entity);

    public IEntityContext update(IEntityContext entity);

    @SuppressWarnings("rawtypes")
    public long count(Class clazz);

    public List<IEntityContext> executeNamedQuery(String namedQuery,
            String paramName, Object paramValue);
}