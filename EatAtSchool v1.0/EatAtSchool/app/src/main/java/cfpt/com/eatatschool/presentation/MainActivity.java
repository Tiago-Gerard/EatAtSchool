package cfpt.com.eatatschool.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cfpt.com.eatatschool.R;

public class MainActivity extends AppCompatActivity {

    // Bouton "Se connecter en tant qu'invité"
    Button btnInvite;

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
