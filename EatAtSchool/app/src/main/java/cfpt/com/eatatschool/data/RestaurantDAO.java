package cfpt.com.eatatschool.data;

import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static cfpt.com.eatatschool.data.DbManager.readRestArray;

public class RestaurantDAO implements HttpThreadAsync.Listeners<Restaurant> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String myUrl = "https://blueflame-studio.com/services/rest/restaurants";

    static Integer idRestaurant;
    static String nomRestaurant;
    static Double longitudeRestaurant;
    static Double lattittudeRestaurant;
    static String siteRestaurant;
    static Integer livrableRestaurant;
    static String conditionLivraisonRestaurant;

    /**
     * Récupère les restaurant disponible dans la base de données
     * @return retourne les restaurant sous forme de liste de restaurant
     */
    public static ArrayList<Restaurant> getAllRestaurant() {
        //String to place our result in
        JsonReader reader;

        //Instantiate new instance of our class
        DbManager.HttpGetRequest getRequest = new DbManager.HttpGetRequest();
        //Perform the doInBackground method, passing in our url
        try {
            reader = getRequest.execute(myUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            reader = new JsonReader(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            reader = new JsonReader(null);
        }
        try {
            return readRestArray(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Restaurant doInBackground() {
        try {
            //Create a URL object holding our url
            URL finalUrl = new URL(myUrl);

            //Create a connection
            HttpURLConnection connection = (HttpURLConnection) finalUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();

            //Create a new InputStreamReader
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            JsonReader jsReader = new JsonReader(reader);
            return readRest(jsReader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onPostExecute(String success) {

    }

    /**
     * Transforme le json reader en restaurant
     * @param reader
     * @return
     * @throws IOException
     */
    public static Restaurant readRest(JsonReader reader) throws IOException {

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("idRestaurant")) {
                idRestaurant = reader.nextInt();
            } else if (name.equals("nomRestaurant")) {
                nomRestaurant = reader.nextString();
            } else if (name.equals("longitudeRestaurant")) {
                longitudeRestaurant = reader.nextDouble();
            } else if (name.equals("latitudeRestaurant")) {
                lattittudeRestaurant = reader.nextDouble();
            } else if (name.equals("siteWebRestaurant")) {
                siteRestaurant = reader.nextString();
            } else if (name.equals("livraisonRestaurant")) {
                livrableRestaurant = reader.nextInt();
            } else if (name.equals("conditionLivraisonRestaurant")) {
                conditionLivraisonRestaurant = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Restaurant(idRestaurant, nomRestaurant, longitudeRestaurant, lattittudeRestaurant, siteRestaurant, livrableRestaurant, conditionLivraisonRestaurant);
    }
}
