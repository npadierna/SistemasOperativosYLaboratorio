package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import android.util.Log;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;

public abstract class WebServiceContext {

	private static final String TAG = WebServiceContext.class.getSimpleName();

	private static final String AMPERSAND = "&";
	private static final String SLASH = "/";
	private static final String EQUAL = "=";

	public static final String CONTENT_TYPE_KEY = "content-type";
	public static final String CONTENT_TYPE_VALUE = "application/json";

	private WebServiceServer webServiceServer;

	public WebServiceContext(WebServiceServer webServiceServer) {
		super();
		this.setWebServiceServer(webServiceServer);
	}

	public WebServiceServer getWebServiceServer() {

		return (this.webServiceServer);
	}

	public void setWebServiceServer(WebServiceServer webServiceServer) {
		this.webServiceServer = webServiceServer;
	}

	public URI buildURIForHTTPMethod(String[] paths,
			Map<String, String> parameters) throws URISyntaxException {
		StringBuilder stringForPaths = new StringBuilder();

		if ((paths != null) && (paths.length != 0)) {
			for (String s : paths) {
				stringForPaths.append(s);
			}
		}
		Log.d(TAG, "Total Paths: " + stringForPaths.toString());

		URI uri = new URI(this.getWebServiceServer().getProtocol(), "", this
				.getWebServiceServer().getIp(), Integer.parseInt(this
				.getWebServiceServer().getPort()), SLASH
				+ this.getWebServiceServer().getContext() + SLASH
				+ this.getWebServiceServer().getWebService()
				+ stringForPaths.toString(),
				this.buildQueriesForPath(parameters), null);

		Log.d(TAG, "URI Content: " + uri.toString());

		return (uri);
	}

	public String formatToJSONArrayString(String jsonArray) {
		StringBuilder newFormatToArray = new StringBuilder(jsonArray);

		int indexOf = jsonArray.indexOf(":[");
		newFormatToArray.delete(0, (indexOf + 1));
		newFormatToArray.deleteCharAt(newFormatToArray.length() - 1);

		return (newFormatToArray.toString());
	}

	private String buildQueriesForPath(Map<String, String> parameters) {
		if ((parameters != null) && (!parameters.isEmpty())) {
			StringBuilder stringForQueries = new StringBuilder();
			Set<String> keySet = parameters.keySet();
			int counter = parameters.size();

			for (String key : keySet) {
				stringForQueries.append(key).append(EQUAL)
						.append(parameters.get(key));
				counter--;

				if (counter >= 1) {
					stringForQueries.append(AMPERSAND);
				}
			}

			return (stringForQueries.toString());
		}

		return (null);
	}
}