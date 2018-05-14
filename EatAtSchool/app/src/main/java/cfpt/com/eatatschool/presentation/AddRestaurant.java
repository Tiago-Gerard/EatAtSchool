package cfpt.com.eatatschool.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cfpt.com.eatatschool.R;
import cfpt.com.eatatschool.data.Outils.ServiceAddEcole;
import cfpt.com.eatatschool.data.Outils.ServiceAddRestaurant;
import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import cfpt.com.eatatschool.domaine.SchoolSerializable;

public class AddRestaurant extends AppCompatActivity implements ServiceAddRestaurant.Callbacks, LocationListener {

    private TextView nom;
    private TextView adresseRestaurant;
    private Address address;
    private TextView localiteNPA;
    private Geocoder g;
    private LocationManager lm;
    private LatLng latLng;
    private List<Address> a;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // Activation de la flèche de retour sur la barre de navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nom = findViewById(R.id.tbxNomRestaurant);
        localiteNPA = findViewById(R.id.tbxLocaliteRestaurant);
        adresseRestaurant = findViewById(R.id.tbxAdresseRestaurant);
        pb = findViewById(R.id.progressBar3);
    }

    @SuppressLint("MissingPermission")
    public void onClickMyLocalisationButton(View v){
        pb.setVisibility(View.VISIBLE);
        final Handler h = new Handler();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, this);
        a = new ArrayList<>();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (latLng != null){
                    h.removeCallbacks(this);
                    g = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        a = g.getFromLocation(latLng.latitude, latLng.longitude, 1);
                        String[] adresse = new String[3];
                        adresse = a.get(0).getAddressLine(0).split(",");
                        adresseRestaurant.setText(adresse[0]);
                        localiteNPA.setText(adresse[1]);
                        pb.setVisibility(View.INVISIBLE);
                        stopGPS();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                h.postDelayed(this, 100);
            }
        };
        runnable.run();
    }

    private void stopGPS(){
        lm.removeUpdates(this);
    }

    public void OnClickButtonAddRestaurant(View v){
        g = new Geocoder(this);
        try {
            List<Address> items = g.getFromLocationName(String.valueOf(adresseRestaurant.getText()), 1);
            address = items.get(0);
            ServiceAddRestaurant.addRestaurant(this, String.valueOf(nom.getText()), address.getLatitude(), address.getLongitude());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("GPS", location.getLatitude() + "  " + location.getLongitude());
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onResponse(RestaurantSerializable restos) {
        Log.i("CREATED ", "post submitted to API.");
    }

    @Override
    public void onFailure() {
        Log.e("ERROR ", "post not submitted to API.");
    }
}
