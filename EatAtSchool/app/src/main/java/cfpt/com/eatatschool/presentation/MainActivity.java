package cfpt.com.eatatschool.presentation;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import cfpt.com.eatatschool.R;

public class MainActivity extends AppCompatActivity {

    // Bouton "Se connecter en tant qu'invité"
    Button btnInvite;

    Button btnConnexion;

    /**
     * Création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupération du bouton dans la vue
        btnInvite = findViewById(R.id.btnInvite);

        btnConnexion = findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // déplacement sur l'activité "Choose School" lors du click
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseSchoolActivity.class);
                startActivity(intent);
            }
        });
    }
}
