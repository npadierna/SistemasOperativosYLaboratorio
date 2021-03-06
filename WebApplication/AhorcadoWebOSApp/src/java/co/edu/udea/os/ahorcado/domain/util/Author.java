package co.edu.udea.os.ahorcado.domain.util;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public final class Author {

    private String name;
    private long id;

    public Author(String name) {
        this.setName(name);
    }

    public String getName() {

        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {

        return (this.id);
    }

    public void setId(long id) {
        this.id = id;
    }
}