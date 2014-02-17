package co.edu.udea.os.ahorcado.web.bean.authors;

import co.edu.udea.os.ahorcado.domain.util.Author;
import java.io.Serializable;
import java.util.ArrayList;

public class AuthorsBean implements Serializable {

    private final static String[] authors;
    private ArrayList<Author> authorList;

    static {
        authors = new String[3];
        authors[0] = "Santiago Gómez Piedrahíta";
        authors[1] = "Neiber de Jesús Padierna Pérez";
        authors[2] = "Andersson García Sotelo";
    }

    public AuthorsBean() {
        this.authorList = new ArrayList<Author>();
        this.obtainAuthorList();
    }

    public ArrayList<Author> getAuthorList() {
        return authorList;
    }

    private void obtainAuthorList() {
        for(int i = 0; i < AuthorsBean.authors.length; i++){
            this.authorList.add(new Author(AuthorsBean.authors[i]));
        }
    }
}