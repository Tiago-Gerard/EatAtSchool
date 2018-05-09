package cfpt.com.eatatschool.presentation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cfpt.com.eatatschool.R;
import cfpt.com.eatatschool.data.Outils.ServiceAddEcole;
import cfpt.com.eatatschool.domaine.SchoolSerializable;

public class AddSchool extends AppCompatActivity implements ServiceAddEcole.Callbacks {

    private TextView nom;
    private TextView adresseEcole;
    private Address address;
    private Geocoder g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_school);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nom = findViewById(R.id.tbxNomEcole);
        adresseEcole = findViewById(R.id.tbxAdresseEcole);


    }

    public void onClickButtonLoc(View v) {
        try {

            List<Address> a = g.getFromLocation(getGPS().latitude, getGPS().longitude, 1);
            adresseEcole.setText(a.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickButtonAdd(View v) {
        g = new Geocoder(this);
        try {
            List<Address> items = g.getFromLocationName(String.valueOf(adresseEcole.getText()), 1);
            address = items.get(0);
            ServiceAddEcole.addSchool(this, String.valueOf(nom.getText()), address.getLatitude(), address.getLongitude());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LatLng getGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        /* Loop over the array backwards, and if you get an accurate location, then break                 out the loop*/
        Location l = null;

        for (int i = providers.size() - 1; i >= 0; i--) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) break;
        }

        return new LatLng(l.getLatitude(), l.getLongitude());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onResponse(SchoolSerializable ecoles) {
        Log.i("CREATED ", "post submitted to API.");
    }

    @Override
    public void onFailure() {
        Log.e("ERROR ", "post not submitted to API.");
    }
}
