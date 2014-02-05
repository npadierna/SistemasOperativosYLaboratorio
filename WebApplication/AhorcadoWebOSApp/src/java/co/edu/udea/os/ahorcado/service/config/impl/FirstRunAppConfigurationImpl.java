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
            Category category = new Category("Comida");
            this.categoryDAO.saveCategory(category);
            category = new Category("Deportes");
            this.categoryDAO.saveCategory(category);
            category = new Category("Hogar");
            this.categoryDAO.saveCategory(category);
            category = new Category("Música");
            this.categoryDAO.saveCategory(category);
            category = new Category("Naturaleza");
            this.categoryDAO.saveCategory(category);
            category = new Category("Ocio");
            this.categoryDAO.saveCategory(category);
            category = new Category("Religión");
            this.categoryDAO.saveCategory(category);
            category = new Category("Ropa");
            this.categoryDAO.saveCategory(category);
            category = new Category("Tecnología");
            this.categoryDAO.saveCategory(category);
            category = new Category("Verbos");
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
            Word word = new Word("Hierba");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Sabroso");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Carta");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Agrio");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Probar");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Deportes */
            word = new Word("Karate");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Taekwondo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Basquet");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Esgrima");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Automovilismo");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Hogar */
            word = new Word("Moderno");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Calefaccion");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Polvo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Sucio");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Limpio");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Música */
            word = new Word("Reggae");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Clasica");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Balada");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Jazz");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Vallenato");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Naturaleza */
            word = new Word("Alud");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Roca");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Conejo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Tronco");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Lluvioso");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Ocio */
            word = new Word("Hincha");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Aplausos");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Sentimental");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Arte");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Tiempo");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Religión */
            word = new Word("Budismo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Evangelismo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Luteranismo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Hinduismo");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Cristianismo");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Ropa */
            word = new Word("Impermeable");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Camison");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Pulsera");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Joyas");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Reloj");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Tecnología */
            word = new Word("Impresora");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Celular");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Teclado");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Internet");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Videojuego");
            this.wordDAO.saveWord(word);
            this.words.add(word);

            /* Category: Verbos */
            word = new Word("Tocar");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Salir");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Llamar");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Pedir");
            this.wordDAO.saveWord(word);
            this.words.add(word);
            word = new Word("Jugar");
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