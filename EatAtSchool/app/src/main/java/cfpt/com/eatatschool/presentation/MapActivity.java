package cfpt.com.eatatschool.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cfpt.com.eatatschool.R;
import cfpt.com.eatatschool.data.Outils.ServiceRestaurant;
import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import cfpt.com.eatatschool.domaine.SchoolSerializable;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, ServiceRestaurant.Callbacks {

    // Variables de classe pour la carte
    private GoogleMap mMap;     // Carte
    private LatLng centreCercle;        // Point autour du quel sera éffectuée la recherche
    private double rayonCercle = 1000;      // Rayon de la zone de recherche
    private SchoolSerializable ecoleChoisie; // Ecole à partir de laquelle s'éffectue la recherche
    private Geocoder g;
    private Address address;
    // Instencie une nouvelle liste de restaurant vide
    private ArrayList<RestaurantSerializable> restoDisponibles;

    /**
     * Création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Récupération du nom de l'école choisie
        Intent intent = getIntent();
        SchoolSerializable ecole = (SchoolSerializable) intent.getSerializableExtra("ecole");
        g = new Geocoder(this);
        try {
            List<Address> items = g.getFromLocationName("Rue des charmilles", 1);
            address = items.get(0);
            address.getLatitude();
            address.getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }

        restoDisponibles = new ArrayList<>();
        executeHttpRequestWithRetrofit();

        //
        ecoleChoisie = ecole;

        // Activation de la flèche de retour sur la barre de navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Obtient le SupportMapFragment et est appelé quand la carte est prête à l'emploi
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void OnClickAddRestaurant(View v){
        Intent i = new Intent(MapActivity.this, AddRestaurant.class);
        startActivity(i);
    }


    /**
     * Retourne sur l'activity précédente
     * @return toujours true
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Affiche la carte avec les information souhaitées
     * @param googleMap carte à gérer
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Assigne la carte à gérer
        mMap = googleMap;

        // Assigne les coordonnées au point central
        centreCercle = new LatLng(ecoleChoisie.getLat(), ecoleChoisie.getLon());

        // Dessine un cercle autour de la zone de recherche
        mMap.addCircle(new CircleOptions()
                .center(centreCercle)
                .radius(rayonCercle)
                .strokeColor(Color.argb(200,0,0,0)));

        // Ajoute un marker à l'emplacement de l'école choisie (centre du cercle)
        mMap.addMarker(new MarkerOptions().position(centreCercle).title(ecoleChoisie.getNom()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title("TEST").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        // Centre la caméra sur l'école
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centreCercle));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(14);
        mMap.animateCamera(zoom);
    }


    //Conversion des degrés en radian
    public double convertRad(double degre){
        return (Math.PI * degre)/180;
    }

    /**
     *
     * @param lat_rest latitude du restaurant à vérifier
     * @param long_rest longitude du restaurant à vérifier
     * @param lat_school latitude de l'école choisie
     * @param long_school longitude de l'école choisie
     * @return retourne la distance entre le restaurant et l'école calculée en M
     */
    public double Distance(double lat_rest, double long_rest, double lat_school, double long_school){

        // Constante du rayon de la terre
        final int rTerre = 6378137; //Rayon de la terre en mètre

        // Converti toutes les valeurs en radian
        double lat_rest_rad = convertRad(lat_rest);
        double long_rest_rad = convertRad(long_rest);
        double lat_school_rad = convertRad(lat_school);
        double long_school_rad = convertRad(long_school);

        // Calcul de la distance entre le restaurant et l'école
        double distance = rTerre * (Math.PI/2 - Math.asin( Math.sin(lat_school_rad) * Math.sin(lat_rest_rad) + Math.cos(long_school_rad - long_rest_rad) * Math.cos(lat_school_rad) * Math.cos(lat_rest_rad)));
        return distance;
    }

    private void executeHttpRequestWithRetrofit(){
        ServiceRestaurant.getRestaurants(this);
    }

    @Override
    public void onResponse(@Nullable List<RestaurantSerializable> restos) {

        // Pour chaque restaurants verifie si il est dans la zone de recherche
        // si oui l'ajoute dans l'ArrayList restoDisponibles
        for (RestaurantSerializable resto: restos) {
            if (Distance(resto.getLatitudeRestaurant(), resto.getLongitudeRestaurant(), ecoleChoisie.getLat(), ecoleChoisie.getLon()) <= rayonCercle){
                restoDisponibles.add(resto);
            }
        }
        // Affiche un marker pour chaque restaurant à proximité
        for (RestaurantSerializable rs: restoDisponibles) {
            LatLng posResto = new LatLng(rs.getLatitudeRestaurant(), rs.getLongitudeRestaurant());
            mMap.addMarker(new MarkerOptions().position(posResto).title(rs.getNomRestaurant()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
    }

    @Override
    public void onFailure() {
        Log.e("FAIL ","Error");
    }
}
