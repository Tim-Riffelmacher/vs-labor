package hska.iwi.eShopMaster.model.businessLogic.microservice.connector;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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

	private <T> HttpEntity executePostCall(String endpoint, T Object, Class<T> clazz)
			throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost(baseUrl + endpoint);
		
        request.setHeader("Content-Type", "application/json");

		Gson gson = new Gson();
		String requestBody = gson.toJson(Object, clazz);
		StringEntity requestEntity = new StringEntity(requestBody);
		request.setEntity(requestEntity);

		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		return entity;
	}

	public <T,R> R getObjectFromPostEndpoint(String endpoint, T Object, Class<T> requestClazz, Class<R> responseClazz) {
		String responseJson = "";
		try {
			HttpEntity entity = executePostCall(endpoint, Object, requestClazz);
			responseJson = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (responseClazz == null) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(responseJson, responseClazz);
	}

	public <T> T getObjectFromGetEndpoint(String endpoint, Class<T> clazz) {
		String responseJson = "";
		try {
			HttpEntity entity = executeGetCall(endpoint);
			responseJson = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (clazz == null) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(responseJson, clazz);
	}
	
	public <T> T getObjectFromGetEndpoint(String endpoint,Type type) {
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
