package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class APICaller {

    public JSONArray GetCars() throws Exception {
        JSONParser parser = new JSONParser();
        Object data = parser.parse(get_http("http://localhost:5000/api/v1/Car/get_all_cars"));
        JSONArray jsonArray = (JSONArray) data;
        return jsonArray;
    }

    public JSONArray GetReservations(int customerId) throws Exception {
        JSONParser parser = new JSONParser();
        Object data = parser.parse(get_http("http://localhost:5000/api/v1/Booking/get_bookings/" + customerId));
        JSONArray jsonArray = (JSONArray) data;
        return jsonArray;
    }

    private String get_http(String url) throws Exception {
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();

    }
}
