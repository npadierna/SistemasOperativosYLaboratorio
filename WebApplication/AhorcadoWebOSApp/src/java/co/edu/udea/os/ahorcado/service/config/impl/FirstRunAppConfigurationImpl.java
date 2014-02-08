package co.edu.udea.os.ahorcado.service.config.impl;

import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.ICategoryWordsDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IPlayerDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IRecordDAO;
import co.edu.udea.os.ahorcado.persistence.dbservice.IWordDAO;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.Player;
import co.edu.udea.os.ahorcado.persistence.entity.Record;
import co.edu.udea.os.ahorcado.persistence.entity.Word;
import co.edu.udea.os.ahorcado.service.config.IFirstRunAppConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class FirstRunAppConfigurationImpl implements IFirstRunAppConfiguration {

    @Autowired()
    private ICategoryDAO categoryDAO;
    @Autowired()
    private ICategoryWordsDAO categoryWordsDAO;
    @Autowired()
    private IPlayerDAO playerDAO;
    @Autowired()
    private IRecordDAO recordDAO;
    @Autowired()
    private IWordDAO wordDAO;
    private List<Word> words;
    private List<Category> categories;

    @Override()
    public void createDefaultData() {
        System.out.println(" * Creating the Data Base default data.");

        this.createDefaultCategories();
        this.createDefaultWords();
        this.createDefaultCategoriesWords();
        this.createDefaultPlayers();
        this.createDefaultRecords();

        //this.test();
    }

    @Override()
    public void createDefaultCategories() {
        System.out.println(" * Creating the default \"CATEGORY\" data.");

        if (this.categoryDAO.countCategories() == 0) {
            Category category = new Category("COMIDA");
            this.categoryDAO.saveCategory(category);
            category = new Category("DEPORTE");
            this.categoryDAO.saveCategory(category);
            category = new Category("HOGAR");
            this.categoryDAO.saveCategory(category);
            category = new Category("MUSICA");
            this.categoryDAO.saveCategory(category);
            category = new Category("NATURALEZA");
            this.categoryDAO.saveCategory(category);
            category = new Category("OCIO");
            this.categoryDAO.saveCategory(category);
            category = new Category("RELIGION");
            this.categoryDAO.saveCategory(category);
            category = new Category("ROPA");
            this.categoryDAO.saveCategory(category);
            category = new Category("TECNOLOGIA");
            this.categoryDAO.saveCategory(category);
            category = new Category("VERBOS");
            this.categoryDAO.saveCategory(category);
        }

        this.categories = this.categoryDAO.findAllCategories();
    }

    @Override()
    public void createDefaultCategoriesWords() {
        System.out.println(" * Creating the default \"CATEGORY_WORDS\" data.");

        if (this.categoryWordsDAO.countCategoriesWords() == 0) {
            int counter = 0;
            for (Category c : this.categories) {
                for (int index = 0; index <= 4; index++) {
                    this.categoryWordsDAO.saveCategoryWords(new CategoryWords(
                            c.getName(),
                            this.words.get(index + counter).getName()));
                }
                counter += 5;
            }
        }
    }

    @Override()
    public void createDefaultPlayers() {
        System.out.println(" * Creating the default \"PLAYER\" data.");

        if (this.playerDAO.countPlayers() == 0) {
            Player player = new Player("npadierna", "123",
                    "npadierna@gmail.com");
            this.playerDAO.savePlayer(player);
            player = new Player("anderssongs5", "465",
                    "anderssongarciasotelo@gmail.com");
            this.playerDAO.savePlayer(player);
        }
    }

    @Override()
    public void createDefaultRecords() {
        System.out.println(" * Creating the default \"RECORD\" data.");

        if (this.recordDAO.countRecords() == 0) {
        }
    }

    @Override()
    public void createDefaultWords() {
        System.out.println(" * Creating the default \"WORDS\" data.");

        if (this.wordDAO.countWords() == 0) {
            this.words = new ArrayList<>();

            /* Category: Comida */
            Word word = new Word("HIERBA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("SABROSO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CARTA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("AGRIO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("PROBAR");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Deportes */
            word = new Word("KARATE");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("TAEKWONDO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("BASQUET");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("ESGRIMA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("AUTOMOVILISMO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Hogar */
            word = new Word("MODERNO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CALEFACCION");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("POLVO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("SUCIO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("LIMPIO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Música */
            word = new Word("REGGAE");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CLASICA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("BALADA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("JAZZ");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("VALLENATO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Naturaleza */
            word = new Word("ALUD");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("ROCA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CONEJO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("TRONCO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("LLUVIOSO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Ocio */
            word = new Word("HINCHA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("APLAUSOS");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("SENTIMENTAL");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("ARTE");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("TIEMPO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Religión */
            word = new Word("BUDISMO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("EVANGELISMO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("LUTERANISMO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("HINDUISMO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CRISTIANISMO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Ropa */
            word = new Word("IMPERMEABLE");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CAMISON");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("PULSERA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("JOYAS");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("RELOJ");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Tecnología */
            word = new Word("IMPRESORA");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("CELULAR");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("TECLADO");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("INTERNET");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("VIDEOJUEGO");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Verbos */
            word = new Word("TOCAR");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("SALIR");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("LLAMAR");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("PEDIR");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("JUGAR");
            this.wordDAO.saveWord(word);
            this.words.add(word);
        }
    }

    private void test() {
        Player p = new Player("test_user", "test_user", "test_user@test.com");
        this.playerDAO.savePlayer(p);

        p.setEmail("test_user2@test.com");
        this.playerDAO.updatePlayer(p);

        this.playerDAO.deletePlayer(p);
        Player temp = this.playerDAO.findPlayer("test_user");

        List<Record> records = this.recordDAO.findAllRecords();
        List<CategoryWords> categoriesWords =
                this.categoryWordsDAO.findAllCategoriesWords();

        p = this.playerDAO.findPlayer("npadierna");
        Category c = this.categoryDAO.findCategory("Deportes");
        Record record = this.recordDAO.findBestRecordForPlayerInCategory(p, c);
    }
}