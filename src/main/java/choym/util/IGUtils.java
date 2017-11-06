package choym.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import choym.model.instagram.IGMedia;

public class IGUtils {
	public static final String MEDIA_URL = "http://instagram.com/%s/media/";

	/**
	 * get IG user's media
	 * 
	 * @param ig_id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static IGMedia getMedia(String ig_id) throws ClientProtocolException, IOException {
		// create http get request, and get response
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(String.format(MEDIA_URL, ig_id));
		HttpResponse response = client.execute(request);

		// extract IG page content
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return new ObjectMapper().readValue(result.toString(), IGMedia.class);
	}

	/**
	 * check if IG exist
	 * 
	 * @param ig_id
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static boolean exist(String ig_id) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(String.format(MEDIA_URL, ig_id));
		HttpResponse response = client.execute(request);
		return response.getStatusLine().getStatusCode() == 200;
	}
}
