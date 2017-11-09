package choym.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import choym.model.instagram.IGMedia;

public class IGUtils {
	public static final String MEDIA_URL = "https://api.instagram.com/v1/users/%s/media/recent/?access_token=%s&count=1";
	
	public static final String SEARCH_USER_URL = "https://api.instagram.com/v1/users/search?q=%s&access_token=%s";
	
	public static final String GET_USER_URL = "https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN";

	/**
	 * get IG user's media
	 * 
	 * @param ig_id
	 * @param instagramAccessToken 
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static IGMedia getMedia(String ig_id, String instagramAccessToken) throws ClientProtocolException, IOException {
		String json = httpGet(String.format(MEDIA_URL, ig_id, instagramAccessToken));
		return new ObjectMapper().readValue(json, IGMedia.class);
	}
	
	@SuppressWarnings("unchecked")
	public static String getIGId(String ig_username, String instagramAccessToken) throws ClientProtocolException, IOException {
		String json = httpGet(String.format(SEARCH_USER_URL, ig_username, instagramAccessToken));
		Map<String, Object> map = new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>(){});
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");
		return list.get(0).get("id").toString();
	}

	/**
	 * check if IG exist
	 * 
	 * @param ig_username
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@SuppressWarnings("unchecked")
	public static boolean exist(String ig_username, String instagramAccessToken) throws ClientProtocolException, IOException {
		String json = httpGet(String.format(SEARCH_USER_URL, ig_username, instagramAccessToken));
		Map<String, Object> map = new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>(){});
		List<Object> list = (List<Object>) map.get("data");
				
		return !list.isEmpty();
	}
	
	/**
	 * send HTTP GET request, and return response body
	 * @param url request url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static String httpGet(String url) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		
		// extract IG page content
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return result.toString();
	}
}
