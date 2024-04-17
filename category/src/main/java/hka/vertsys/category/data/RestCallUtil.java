package hka.vertsys.category.data;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class RestCallUtil {

	String baseUrl;

	public RestCallUtil(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private HttpEntity executeGetCall(String endpoint) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(baseUrl + endpoint);

		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		return entity;
	}

	public <T> T getObjectFromGetEndpoint(String endpoint, Type type) {
		String responseJson = "";
		try {
			HttpEntity entity = executeGetCall(endpoint);
			responseJson = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.fromJson(responseJson, type);
	}

}
