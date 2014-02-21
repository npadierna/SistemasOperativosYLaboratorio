package co.edu.udea.os.ahorcado.web.bean.authors;

import co.edu.udea.os.ahorcado.domain.util.Author;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class AuthorsBean implements Serializable {

    private static final long serialVersionUID = 1252002994169145344L;
    private final static String[] authors;
    private ArrayList<Author> authorList;

    static {
        authors = new String[3];
        authors[0] = "Santiago Gómez Piedrahíta";
        authors[1] = "Neiber de Jesús Padierna Pérez";
        authors[2] = "Andersson García Sotelo";
    }

    public AuthorsBean() {
        this.authorList = new ArrayList<>();

        this.obtainAuthorsList();
    }

    public ArrayList<Author> getAuthorList() {

        return (this.authorList);
    }

    private void obtainAuthorsList() {
        for (int i = 0; i < AuthorsBean.authors.length; i++) {
            this.authorList.add(new Author(AuthorsBean.authors[i]));
        }
    }
}