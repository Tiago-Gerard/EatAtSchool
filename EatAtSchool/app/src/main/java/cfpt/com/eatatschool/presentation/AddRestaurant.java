package cfpt.com.eatatschool.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cfpt.com.eatatschool.R;

public class AddRestaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // Activation de la flèche de retour sur la barre de navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
