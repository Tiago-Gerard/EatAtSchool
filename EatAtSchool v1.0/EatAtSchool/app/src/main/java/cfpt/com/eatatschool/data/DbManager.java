package cfpt.com.eatatschool.data;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cfpt.com.eatatschool.domaine.Restaurant;
import cfpt.com.eatatschool.domaine.School;

/**
 * Created by ADMINELEVE on 15.03.2018.
 */

public class DbManager {

    static Integer idEcole;
    static String nomEcole;
    static Double longitudeEcole;
    static Double lattittudeEcole;

    static Integer idRestaurant;
    static String nomRestaurant;
    static Double longitudeRestaurant;
    static Double lattittudeRestaurant;
    static String siteRestaurant;
    static Integer livrableRestaurant;
    static String conditionLivraisonRestaurant;

    /**
     * Récupère les écoles disponible dans la base de données
     * @return retourne les écoles sous forme de liste d'école
     */
    public static ArrayList<School> getSchools() {
        //Some url endpoint that you may have
        String myUrl = "http://10.134.98.158/rest/ecoles";
        //String to place our result in
        JsonReader reader;

        //Instantiate new instance of our class
        HttpGetRequest getRequest = new HttpGetRequest();
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
            return readSchoolArray(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Récupère les restaurant disponible dans la base de données
     * @return retourne les restaurant sous forme de liste de restaurant
     */
    public static ArrayList<Restaurant> getRestaurant() {
        //Some url endpoint that you may have
        String myUrl = "http://10.134.98.158/rest/restaurants";
        //String to place our result in
        JsonReader reader;

        //Instantiate new instance of our class
        HttpGetRequest getRequest = new HttpGetRequest();
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

    /**
     * Fait une requette http sur le service rest en get
     */
    public static class HttpGetRequest extends AsyncTask<String, Void, JsonReader> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected JsonReader doInBackground(String... params) {
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);

                //Create a connection
                HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

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
                return jsReader;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(JsonReader result) {
            super.onPostExecute(result);
        }
    }

    /**
     * Transforme le json en liste d'école
     * @param reader
     * @return
     * @throws IOException
     */
    public static ArrayList<School> readSchoolArray(JsonReader reader) throws IOException {
        ArrayList<School> arraShool = new ArrayList<School>();

        reader.beginArray();
        while (reader.hasNext()) {
            arraShool.add(readSchool(reader));
        }
        reader.endArray();
        return arraShool;
    }

    /**
     * transforme le json en école
     * @param reader
     * @return
     * @throws IOException
     */
    public static School readSchool(JsonReader reader) throws IOException {

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                idEcole = reader.nextInt();
            } else if (name.equals("nom")) {
                nomEcole = reader.nextString();
            } else if (name.equals("lon")) {
                longitudeEcole = reader.nextDouble();
            } else if (name.equals("lat")) {
                lattittudeEcole = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new School(idEcole, nomEcole, longitudeEcole, lattittudeEcole);
    }

    /**
     * Lis le json reader et le transforme en liste de restaurant
     * @param reader
     * @return
     * @throws IOException
     */
    public static ArrayList<Restaurant> readRestArray(JsonReader reader) throws IOException {
        ArrayList<Restaurant> arraRest = new ArrayList<Restaurant>();

        reader.beginArray();
        while (reader.hasNext()) {
            arraRest.add(readRest(reader));
        }
        reader.endArray();
        return arraRest;
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

