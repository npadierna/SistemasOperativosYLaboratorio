package co.edu.udea.os.ahorcado.service.config.impl;

import co.edu.udea.os.ahorcado.service.config.IFirstRunAppConfiguration;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class FirstRunAppConfigurationImpl implements IFirstRunAppConfiguration {

    @Override()
    public void createDefaultData() {
        System.out.println(" * Creating the Data Base default data.");
    }

    @Override()
    public void createDefaultCategories() {
        System.out.println(" * Creating the default \"CATEGORY\" data.");
    }

    @Override()
    public void createDefaultCategoriesWords() {
        System.out.println(" * Creating the default \"CATEGORY_WORDS\" data.");
    }

    @Override()
    public void createDefaultPlayers() {
        System.out.println(" * Creating the default \"PLAYER\" data.");
    }

    @Override()
    public void createDefaultRecords() {
        System.out.println(" * Creating the default \"RECORD\" data.");
    }

    @Override()
    public void createDefaultWords() {
        System.out.println(" * Creating the default \"WORDS\" data.");
    }
}