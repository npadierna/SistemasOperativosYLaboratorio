package co.edu.udea.os.ahorcado.service.webservice;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public final class WebServiceContext {

    private WebServiceContext() {
        super();
    }

    public static final class CategoryWSContext {

        public static final String ROOT_PATH = "/categories";
    }

    public static final class CategoryWordsWSContext {

        public static final String ROOT_PATH = "/categorieswords";
    }

    public static final class PlayerWSContext {

        public static final String ROOT_PATH = "/players";
    }

    public static final class RecordWSContext {

        public static final String ROOT_PATH = "/records";
    }

    public static final class WordWSContext {

        public static final String ROOT_PATH = "/words";
    }
}