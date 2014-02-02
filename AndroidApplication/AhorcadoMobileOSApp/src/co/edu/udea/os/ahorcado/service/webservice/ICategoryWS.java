package co.edu.udea.os.ahorcado.service.webservice;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Category;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public interface ICategoryWS {

    public List<Category> findAllCategories();
}