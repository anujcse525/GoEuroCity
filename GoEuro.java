package GoEuroCity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class GoEuro {

	private final static String URL_PREFIX = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("USAGE : <CITY_NAME>");
			return;
		}

		StringBuilder city = new StringBuilder();
		
		for(String _tmp : args){
			city.append(_tmp);
		}
		
		String urlWithCityName = URL_PREFIX + city.toString();

		System.out.println("Complete URL : " + urlWithCityName);

		JSONObject jsonObj = readJsonFromGivenURL(urlWithCityName);

		System.out.println(jsonObj.toString());
	}

	private static JSONObject readJsonFromGivenURL(String url) {
		JSONObject jsonObj = null;

		try {
			InputStream is = new URL(url).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String jsonResponse = readJsonResponse(br);
			if(jsonResponse == null || jsonResponse.equals("")){
				System.out.println("Information from URL : "  + url + " is empty");
			}
			jsonObj = new JSONObject(jsonResponse.substring(1, jsonResponse.length() - 1));
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException occured : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException occured : " + e.getMessage());
		} catch (JSONException e) {
			System.out.println("JSONException occured : " + e.getMessage());
		}
		return jsonObj;
	}

	private static String readJsonResponse(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		String lineRead = "";
		while ((lineRead = br.readLine()) != null) {
			sb.append(lineRead);
		}
		return sb.toString();
	}

}
