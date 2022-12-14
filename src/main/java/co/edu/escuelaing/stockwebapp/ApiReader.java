package co.edu.escuelaing.stockwebapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiReader {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=NZZ1BLMFLGUJA0OC";

    public static String getStock(String stock, String type) throws IOException {
        updateURL(stock,type);
        System.out.println(GET_URL);
        System.out.println("stock = " + stock + ", type = " + type);
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "Failed";
    }

    public static void updateURL(String stock,String type){
        switch (type){
            case "Time Series (60min)":
                GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+stock+"&interval=60min&apikey=NZZ1BLMFLGUJA0OC";
                break;
            case "Time Series (Daily)":
                GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+stock+"&apikey=NZZ1BLMFLGUJA0OC";
                break;
            case "Weekly Time Series":
                GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="+stock+"&apikey=NZZ1BLMFLGUJA0OC";
                break;
            case "Monthly Time Series":
                GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol="+stock+"&apikey=NZZ1BLMFLGUJA0OC";
                break;
        }
    }

}
