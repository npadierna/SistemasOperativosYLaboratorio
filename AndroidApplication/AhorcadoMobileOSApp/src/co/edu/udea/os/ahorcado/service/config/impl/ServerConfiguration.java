package co.edu.udea.os.ahorcado.service.config.impl;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import co.edu.udea.os.ahorcado.service.config.IServerConfiguration;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class ServerConfiguration implements IServerConfiguration {

	private static final String TAG = ServerConfiguration.class.getSimpleName();
	private static final String CONTEXT = "context";
	private static final String IP = "ip";
	private static final String PORT = "port";
	private static final String PROTOCOL = "protocol";
	private static final String WEB_SERVICE = "webservice";

	public ServerConfiguration() {
		super();
	}

	public WebServiceServer loadServerConfiguration(Context context,
			int xmlResourceId) throws XmlPullParserException, IOException {
		Log.d(TAG,
				"Creating the Web Services Server from: "
						+ Integer.toString(xmlResourceId));

		XmlResourceParser xmlResourceParser = context.getResources().getXml(
				xmlResourceId);
		WebServiceServer config = new WebServiceServer();

		xmlResourceParser.next();
		int eventType = xmlResourceParser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				String name = xmlResourceParser.getName();
				if (name.equals(CONTEXT)) {
					config.setContext(xmlResourceParser.getAttributeValue(0));
				} else if (name.equals(IP)) {
					config.setIp(xmlResourceParser.getAttributeValue(0));
				} else if (name.equals(PORT)) {
					config.setPort(xmlResourceParser.getAttributeValue(0));
				} else if (name.equals(PROTOCOL)) {
					config.setProtocol(xmlResourceParser.getAttributeValue(0));
				} else if (name.equals(WEB_SERVICE)) {
					config.setWebService(xmlResourceParser.getAttributeValue(0));
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			case XmlPullParser.TEXT:
				break;
			}
			xmlResourceParser.next();
			eventType = xmlResourceParser.getEventType();
		}
		Log.i(TAG, "Web Serives Server Configuration created.");
		Log.d(TAG, config.toString());

		return (config);
	}
}