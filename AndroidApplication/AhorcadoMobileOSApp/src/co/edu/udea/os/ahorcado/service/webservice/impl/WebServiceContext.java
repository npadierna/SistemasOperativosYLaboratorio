package co.edu.udea.os.ahorcado.service.webservice.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import co.edu.udea.os.ahorcado.service.config.impl.WebServiceServer;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
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

	protected HttpEntity executeHTTPMethod(String[] paths,
			Map<String, String> parameters, HttpRequestBase httpRequestBase)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();

		httpRequestBase.setURI(this.buildURIForHTTPMethod(paths, parameters));
		httpRequestBase.setHeader(WebServiceContext.CONTENT_TYPE_KEY,
				WebServiceContext.CONTENT_TYPE_VALUE);

		return (httpClient.execute(httpRequestBase).getEntity());
	}

	protected String formatToJSONArrayString(String jsonArray) {
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

	private URI buildURIForHTTPMethod(String[] paths,
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
}