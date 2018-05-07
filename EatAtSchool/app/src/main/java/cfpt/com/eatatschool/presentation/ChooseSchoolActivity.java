package cfpt.com.eatatschool.presentation;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cfpt.com.eatatschool.R;
import cfpt.com.eatatschool.data.Outils.ServiceEcole;
import cfpt.com.eatatschool.domaine.SchoolSerializable;

public class ChooseSchoolActivity extends AppCompatActivity implements ServiceEcole.Callbacks{

    // Bouton "Recherche des restaurants
    Button findSchool;

    // Liste déroulante contenet les écoles
    Spinner listeEcoles;

    List<SchoolSerializable> lstecoles;

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
        lstecoles = new ArrayList<>();
        // Activation de la flèche de retour sur la barre de navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        executeHttpRequestWithRetrofit();

        // Récupération des éléments de la vue
        findSchool = findViewById(R.id.btnChercher);
        listeEcoles = findViewById(R.id.spEcoles);

        // Charge les écoles dans la liste déroulante
        //loadSchool();

        // Déplacement sur l'activity "Map" lors du click avec le nom de l'école sélectionnée
        findSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseSchoolActivity.this, MapActivity.class);
                for (SchoolSerializable s: lstecoles) {
                    if (listeEcoles.getSelectedItem().toString().equals(s.getNom())){
                        intent.putExtra("ecole", s);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private void executeHttpRequestWithRetrofit(){
        this.loadSchool();
        ServiceEcole.getSchool(this);
    }

    /**
     * Charge les écoles dans la liste déroulante
     */
    public void loadSchool(){
        /*ArrayList<School> schoolList = DbManager.getSchools();
        ArrayList<String> schoolNames = new ArrayList<>();
        for (School s: schoolList) {
            schoolNames.add(s.GetNom());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,schoolNames);
        listeEcoles.setAdapter(adapter);*/
    }

    @Override
    public void onResponse(@Nullable List<SchoolSerializable> ecoles) {
        ArrayList<String> schoolNames = new ArrayList<>();
        lstecoles = ecoles;
        for (SchoolSerializable s: ecoles) {
            schoolNames.add(s.getNom());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,schoolNames);
        listeEcoles.setAdapter(adapter);
    }

    @Override
    public void onFailure() {
        Log.e("FAIL ","Error");
    }
}
