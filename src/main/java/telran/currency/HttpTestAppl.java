package telran.currency;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class HttpTestAppl {

	public static void main(String[] args) throws Exception {
		HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
        		new URI("https://data.fixer.io/api/latest?access_key=49c071d0e1955e57831f9a34432b69c3"))
        		.build();
	    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
	    System.out.println(response.body());
	    JSONObject jsonObject = new JSONObject(response.body());
	    JSONObject jsonRates = jsonObject.getJSONObject("rates");
	    String[]codes = {"USD","EUR","ILS","AZN"};
	    Map<String,Double> map = Arrays.stream(codes)
	    		.collect(Collectors.toMap(c -> c, c -> jsonRates.getDouble(c)));
	    System.out.println(map);
	    };
	}


