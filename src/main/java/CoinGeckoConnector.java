import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Michael Schmidt
 * CS2212 - Intro to Software Engineering
 * @purpose connector class to streamline communication with CoinGeckoAPI
 */
public class CoinGeckoConnector {

    /**
     * method to determine of a coin exists within coinGecko
     * @param coinName full id of coin in question
     * @return whether coin is valid
     */
    public static boolean coinExists(String coinName){
        try {
            // Creates a URL object with proper strings
            URL url = new URL
                    ("https://api.coingecko.com/api/v3/simple/price?ids=" + coinName
                            + "&vs_currencies=usd");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");// Check server status
            connection.connect(); // Connect to server

            String infoString = "";
            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                infoString = infoString + sc.nextLine();
            }
            sc.close();
            if (infoString.equals("{}")){
                return false;
            }
            return true;
        } catch (Exception a) {
            System.out.println(a);
        }
        return false;
    }

    /**
     * basic update call for a hashmap of coin objects
     * @param coinsList list of all coins in use
     * @param currencyType currency to provide information in
     * @param curMap current HashMap of coins
     * @return a new hashmap with updated price values
     */
    public static HashMap<String, Coin> basicCall(String[] coinsList, String currencyType, HashMap<String, Coin> curMap) {
        try {
            // Converting coin list to a ',' seperated string (problem here is that its expecting to get the full names) Adapter might be placed before this layer
            String callString = "";
            for (int i = 0; i < coinsList.length - 1; i++) {
                callString += coinsList[i] + ",";
            }
            callString += coinsList[coinsList.length - 1];


            // Creates a URL object with proper strings
            URL url = new URL
                    ("https://api.coingecko.com/api/v3/simple/price?ids=" + callString
                            + "&vs_currencies=" + currencyType);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");// Check server status
            connection.connect(); // Connect to server

            String infoString = "";
            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                infoString = infoString + sc.nextLine();
            }
            sc.close();

            JSONParser parser = new JSONParser();
            JSONObject apiReturnJSON = (JSONObject) parser.parse(infoString);


            for (int i = 0; i < coinsList.length; i++) {

                //info on a specific coin
                JSONObject coinInfo = (JSONObject) apiReturnJSON.get(coinsList[i]);

                if (curMap.containsKey(coinsList[i])) { // If interested in this object
                    curMap.get(coinsList[i]).setPrice(((Number)(coinInfo.get(currencyType))).doubleValue());
                } else {
                    Coin newCoin = new Coin("TICKERHOLDER", coinsList[i],
                             ((Number)(coinInfo.get(currencyType))).doubleValue());
                    curMap.put(coinsList[i], newCoin);
                }

            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return curMap;
    }
}
