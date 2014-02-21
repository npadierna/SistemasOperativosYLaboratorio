package co.edu.udea.os.ahorcado.persistence.entity;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface IEntityContext {

    public Object getKey();

    public void setKey(Object key);
}