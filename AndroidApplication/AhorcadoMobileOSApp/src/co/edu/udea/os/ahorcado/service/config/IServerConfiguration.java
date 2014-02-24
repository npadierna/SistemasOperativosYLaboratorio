package co.edu.udea.os.ahorcado.service.config;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public interface IServerConfiguration {

	public WebServiceServer loadServerConfiguration(Context context,
			int xmlResourceId) throws XmlPullParserException, IOException;
}