package co.edu.udea.os.ahorcado.service.config;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;

public interface IServerConfiguration {

	public WebServiceServer loadServerConfiguration(Context context,
			int xmlResourceId) throws XmlPullParserException, IOException;
}