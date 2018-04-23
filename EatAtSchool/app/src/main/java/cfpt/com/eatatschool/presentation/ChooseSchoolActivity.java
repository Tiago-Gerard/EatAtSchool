package cfpt.com.eatatschool.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import cfpt.com.eatatschool.R;
import cfpt.com.eatatschool.data.DbManager;
import cfpt.com.eatatschool.domaine.School;

public class ChooseSchoolActivity extends AppCompatActivity {

    // Bouton "Recherche des restaurants
    Button findSchool;

    // Liste déroulante contenet les écoles
    Spinner listeEcoles;

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
     * Création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school);

        // Activation de la flèche de retour sur la barre de navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Récupération des éléments de la vue
        findSchool = findViewById(R.id.btnChercher);
        listeEcoles = findViewById(R.id.spEcoles);

        // Charge les écoles dans la liste déroulante
        loadSchool();

        // Déplacement sur l'activity "Map" lors du click avec le nom de l'école sélectionnée
        findSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseSchoolActivity.this, MapActivity.class);
                intent.putExtra("nomEcole", listeEcoles.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }

    /**
     * Charge les écoles dans la liste déroulante
     */
    public void loadSchool(){
        ArrayList<School> schoolList = DbManager.getSchools();
        ArrayList<String> schoolNames = new ArrayList<>();
        for (School s: schoolList) {
            schoolNames.add(s.GetNom());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,schoolNames);
        listeEcoles.setAdapter(adapter);
    }
}
