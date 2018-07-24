package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String nom;
    String prenom;
    String mail;
    String password;
    String passwordCheck;
    String mandat;
    String circonscription;
    String departement;
    String commune;

    Button inscriptionButton;
    EditText nomEditText;
    EditText prenomEditText;
    EditText mailEditText;
    EditText passwordEditText;
    EditText passwordCheckEditText;
    Spinner mandatSpinner;
    EditText circonscriptionEditText;
    EditText departementEditText;
    EditText communeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //Sérialisation
        inscriptionButton =  findViewById(R.id.inscriptionBouton);
        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        mailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordCheckEditText = findViewById(R.id.passwordCheckEditText);

        mandatSpinner = findViewById(R.id.mandatSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mandat_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mandatSpinner.setAdapter(adapter);
        mandatSpinner.setOnItemSelectedListener(this);
        circonscriptionEditText = findViewById(R.id.circonscriptionEditText);
        departementEditText = findViewById(R.id.departementEditText);
        communeEditText = findViewById(R.id.communeEditText);

        //Listener du Bouton
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean verif = true;
                nom = nomEditText.getText().toString();
                prenom = prenomEditText.getText().toString();
                mail = mailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                passwordCheck = passwordCheckEditText.getText().toString();
                mandat = mandatSpinner.getSelectedItem().toString();
                circonscription = circonscriptionEditText.getText().toString();
                departement = departementEditText.getText().toString();
                commune = communeEditText.getText().toString();

                if(nom.matches("") || prenom.matches("") || mail.matches("") ||
                        password.matches("") || passwordCheck.matches("") || mandat.matches("Sélectionnez votre type de mandat")
                        || circonscription.matches("") || departement.matches("") || commune.matches(""))
                {
                    Toast.makeText(InscriptionActivity.this,"Vous devez renseigner tous les champs",Toast.LENGTH_SHORT).show();
                    verif=false;
                }
                if(!password.equals(passwordCheck)){
                    Toast.makeText(InscriptionActivity.this,"Votre mot de passe ne correspond pas",Toast.LENGTH_SHORT).show();
                    verif=false;
                }
                if(verif){
                    Intent intentInscription = new Intent(InscriptionActivity.this,AccueilActivity.class);
                    /* TODO : Insérer le profil créé dans la BDD

                     */
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
