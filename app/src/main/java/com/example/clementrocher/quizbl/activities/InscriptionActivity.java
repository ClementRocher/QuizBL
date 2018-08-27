package com.example.clementrocher.quizbl.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.example.clementrocher.quizbl.models.Utilisateur;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;

    //Informations d'inscription
    String nom;
    String prenom;
    String mail;
    String password;
    String passwordCheck;
    String mandat;
    String circonscription;
    String departement;
    String commune;

    Utilisateur utilisateur;

    //Containers
    Button inscriptionButton;
    EditText nomEditText;
    EditText prenomEditText;
    EditText mailEditText;
    EditText passwordEditText;
    EditText passwordCheckEditText;
    EditText circonscriptionEditText;
    EditText departementEditText;
    EditText communeEditText;
    TextView alertbox_title;
    Dialog dialog;
    Spinner mandatSpinner;
    Button conditionsButton;
    Button quitterButton;
    CheckBox accepterConditionsCheckbox;


    //Database
    private DatabaseReference db;
    private DatabaseReference utilisateurReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE);

        //Instanciation DB
        db = FirebaseDatabase.getInstance().getReference();
        utilisateurReference = FirebaseDatabase.getInstance().getReference("utilisateurs");

        //Instanciation
        inscriptionButton = findViewById(R.id.inscriptionBouton);
        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        mailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordCheckEditText = findViewById(R.id.passwordCheckEditText);
        conditionsButton = findViewById(R.id.conditionsButton);
        mandatSpinner = findViewById(R.id.mandatSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mandat_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mandatSpinner.setAdapter(adapter);
        accepterConditionsCheckbox = findViewById(R.id.accepterConditionsCheckBox);

        //Sérialisation
        mandatSpinner.setOnItemSelectedListener(this);
        circonscriptionEditText = findViewById(R.id.circonscriptionEditText);
        departementEditText = findViewById(R.id.departementEditText);
        communeEditText = findViewById(R.id.communeEditText);

        conditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pop Up des conditions d'utilisations
                dialog = new Dialog(InscriptionActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.conditions_pop_up);
                dialog.setCanceledOnTouchOutside(false);
                alertbox_title = dialog.findViewById(R.id.alertbox_title);
                alertbox_title.setText("Conditions légales d'utilisation");

                quitterButton = dialog.findViewById(R.id.quitterButton);
                quitterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                /*
                TODO : Enlever le Lorem Ipsum avant version finale
                 */
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Listener du Bouton
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean verif = true;

                //Récupération des infos du formulaire
                nom = nomEditText.getText().toString();
                prenom = prenomEditText.getText().toString();
                mail = mailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                passwordCheck = passwordCheckEditText.getText().toString();
                mandat = mandatSpinner.getSelectedItem().toString();
                circonscription = circonscriptionEditText.getText().toString();
                departement = departementEditText.getText().toString();
                commune = communeEditText.getText().toString();

                //Condition pour s'assurer que tout soit rempli, affichage d'un Toast d'erreur sinon
                if (nom.matches("") || prenom.matches("") || mail.matches("") ||
                        password.matches("") || passwordCheck.matches("") || mandat.matches("Sélectionnez votre type de mandat")
                        || circonscription.matches("") || departement.matches("") || commune.matches("")) {
                    Toast.makeText(InscriptionActivity.this, "Vous devez renseigner tous les champs", Toast.LENGTH_SHORT).show();
                    verif = false;
                }

                //Condition pour que le password et le passwordCheck soient les mêmes, Toast d'erreur sinon
                if (!password.equals(passwordCheck)) {
                    Toast.makeText(InscriptionActivity.this, "Votre mot de passe ne correspond pas", Toast.LENGTH_SHORT).show();
                    verif = false;
                }

                if(!(accepterConditionsCheckbox.isChecked())){
                    Toast.makeText(InscriptionActivity.this,"Vous n'avez pas accepté les conditions d'utilisations",Toast.LENGTH_SHORT).show();
                    verif = false;
                }

                //Si les conditions sont validées
                if (verif) {

                    sharedPreferences
                            .edit()
                            .putString(PREFS_NOM, nom)
                            .putString(PREFS_PRENOM, prenom)
                            .putString(PREFS_MAIL,mail)
                            .apply();

                    utilisateur = new Utilisateur(nom, prenom, mail, password, mandat, circonscription, departement, commune);

                    utilisateurReference.push().setValue(utilisateur);
                    Intent intentInscription = new Intent(InscriptionActivity.this, AccueilActivity.class);
                    startActivity(intentInscription);
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
