package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.util.List;

import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;
import co.edu.udea.os.ahorcado.service.webservice.ICategoryWS;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
public class CategoryWS extends WebServiceContext implements ICategoryWS {

	private static final String TAG = CategoryWS.class.getSimpleName();

	public CategoryWS(WebServiceServer webServiceServer) {
		super(webServiceServer);
	}

	@Override()
	public List<Category> findAllCategories() {

		return (null);
	}
}