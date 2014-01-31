package co.edu.udea.os.ahorcado.service.webservice;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public final class WebServiceContext {

    public static final String ROOT = "/rest";

    private WebServiceContext() {
        super();
    }

    public static final class CategoryWSContext {

        public static final String ROOT_PATH = "/categories";
    }

    public static final class CategoryWordsWSContext {

        public static final String CATEGORY_ALL_WORDS_PATH = "/categories/allwords";
        public static final String CATEGORY_ONE_WORD_PATH = "/categories/oneword";
        public static final String ROOT_PATH = "/categorieswords";
    }

    public static final class PlayerWSContext {

        public static final String PLAYER_LOGIN_PATH = "/login";
        public static final String ROOT_PATH = "/players";
    }

    public static final class RecordWSContext {

        public static final String CATEGORY_ALL_BEST_RECORDS_PATH = "/categories/allrecords";
        public static final String CATEGORY_BEST_RECORD_PATH = "/categories/bestrecord";
        public static final String PLAYER_ALL_RECORDS_PATH = "/players/allrecords";
        public static final String PLAYER_BEST_RECORD_FOR_CATEGORY_PATH = "/players/bestrecord";
        public static final String ROOT_PATH = "/records";
    }

    public static final class WordWSContext {

        public static final String ROOT_PATH = "/words";
    }
}