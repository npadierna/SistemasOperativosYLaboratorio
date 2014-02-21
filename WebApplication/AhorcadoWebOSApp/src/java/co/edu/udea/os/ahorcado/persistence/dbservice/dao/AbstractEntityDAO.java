package co.edu.udea.os.ahorcado.persistence.dbservice.dao;

import co.edu.udea.os.ahorcado.persistence.dbservice.IEntityDAO;
import co.edu.udea.os.ahorcado.persistence.entity.IEntityContext;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceContext;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public abstract class AbstractEntityDAO implements IEntityDAO {

    @PersistenceContext(unitName = "AhorcadoWebOSAppPU")
    protected EntityManager entityManager;

    public AbstractEntityDAO() {
        super();
    }

    public EntityManager getEntityManager() {

        return (this.entityManager);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override()
    public IEntityContext delete(IEntityContext entity) {
        IEntityContext found = null;
        try {
            found = this.find(entity.getClass(),
                    entity.getKey());
            this.getEntityManager().remove(found);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            System.out.println(" - Fatal error while the DAO was trying delete a entity.");
            e.printStackTrace();
        }
        this.getEntityManager().flush();

        return (found);
    }

    @Override()
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object findAll(Class clazz) {
        Query query;
        List<IEntityContext> entities = null;

        try {
            query = this.getEntityManager().createQuery("FROM "
                    + clazz.getSimpleName());
            entities = (List<IEntityContext>) query.getResultList();
        } catch (IllegalArgumentException | IllegalStateException | LockTimeoutException | PessimisticLockException | QueryTimeoutException | TransactionRequiredException e) {
            System.out.println(" - Fatal error while the DAO was trying to recover a list of entities from Data Base.");
            e.printStackTrace();
        }

        return (entities);
    }

    @Override()
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object findByAttributes(Class clazz, Object... attributes) {
        if (attributes.length % 2 != 0) {
            throw new IllegalArgumentException("The number of the arguments for attributes is incorrect: "
                    + attributes.length);
        }

        StringBuilder queryDetail = new StringBuilder();
        for (int pos = 0; pos < attributes.length; pos += 2) {
            if (pos >= 2) {
                queryDetail.append(" AND ");
            } else {
                queryDetail.append(" WHERE ");
            }

            queryDetail.append("o.").append(attributes[pos]);
            if (attributes[pos + 1] instanceof Boolean) {
                queryDetail.append(" = :");
            } else {
                queryDetail.append(" LIKE :");
            }
            queryDetail.append(this.removeDot((String) attributes[pos]));
        }

        Query query = this.getEntityManager().createQuery("FROM "
                + clazz.getSimpleName() + " AS o" + queryDetail.toString());

        for (int pos = 0; pos < attributes.length; pos += 2) {
            query.setParameter(this.removeDot((String) attributes[pos]),
                    attributes[pos + 1]);
        }

        return (query.getResultList());
    }

    @Override()
    @SuppressWarnings({"rawtypes", "unchecked"})
    public IEntityContext find(Class clazz, Object key) {
        IEntityContext entity = null;
        try {
            entity = (IEntityContext) this.getEntityManager().find(clazz, key);
        } catch (IllegalArgumentException e) {
            System.out.println(" - Fatal error while the DAO was trying to find or search an entity.");
            e.printStackTrace();
        }

        return (entity);
    }

    @Override()
    public Object save(IEntityContext entity) {
        try {
            this.getEntityManager().persist(entity);
            this.getEntityManager().flush();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            System.out.println(" - Fatal error while the DAO was trying to persist or save a entity.");
            e.printStackTrace();
        }
        this.getEntityManager().flush();

        return (entity.getKey());
    }

    @Override()
    public IEntityContext update(IEntityContext entity) {
        try {
            this.getEntityManager().merge(entity);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            System.out.println(" - Fatal error while the DAO was trying to update or refresh a entity.");
            e.printStackTrace();
        }
        this.getEntityManager().flush();

        return (entity);
    }

    @Override()
    @SuppressWarnings({"rawtypes", "unchecked"})
    public long count(Class clazz) {
        CriteriaQuery criteriaQuery = this.getEntityManager().getCriteriaBuilder().createQuery();
        Root<IEntityContext> root = criteriaQuery.from(clazz);
        criteriaQuery.select(this.getEntityManager().getCriteriaBuilder().count(root));
        Query query = this.getEntityManager().createQuery(criteriaQuery);

        return (((Long) query.getSingleResult()).longValue());
    }

    @Override()
    @SuppressWarnings("unchecked")
    public List<IEntityContext> executeNamedQuery(String namedQuery,
            String paramName, Object paramValue) {
        List<IEntityContext> resultList = null;

        try {
            Query query = this.getEntityManager().createNamedQuery(namedQuery);
            query.setParameter(paramName, paramValue);
            resultList = query.getResultList();
        } catch (IllegalArgumentException e) {
            System.out.println(" - Fatal error while the DAO was trying to execute the named query.");
            e.printStackTrace();
        }

        return (resultList);
    }

    private String removeDot(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int index = stringBuilder.indexOf(".");

        while (index > -1) {
            stringBuilder.deleteCharAt(index);
            index = stringBuilder.indexOf(".");
        }

        return (stringBuilder.toString());
    }
}