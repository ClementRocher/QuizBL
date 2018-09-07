package com.example.clementrocher.quizbl.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {


    TextView nomTextView;
    TextView prenomTextView;
    TextView mandatTextView;
    TextView mailTextView;
    ImageButton retourImageButton;
    TextView modifierMailTextView;
    TextView deconnexionTextView;
    TextView aideTextView;
    Button retourButton;
    Button decoButton;
    Dialog dialogDeco;
    TextView alertbox_quit_text;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;

    DatabaseReference utilisateurReference;

    String nomProfil;
    String prenomProfil;
    String mailProfil;
    String retrieveMandat;
    String retrieveCirconscription;
    String retrieveDepartement;
    String retrieveCommune;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        utilisateurReference = FirebaseDatabase.getInstance().getReference("utilisateurs");

        //Instanciation

        nomTextView = findViewById(R.id.nomTextView);
        mandatTextView = findViewById(R.id.mandatTextView);
        mailTextView = findViewById(R.id.mailTextView);
        retourImageButton = findViewById(R.id.retourImageButton);
        modifierMailTextView = findViewById(R.id.textInfosTextView);
        deconnexionTextView = findViewById(R.id.deconnexionTextView);
        aideTextView = findViewById(R.id.aideTextView);


        //Setters

        modifierMailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditMail = new Intent(ProfilActivity.this, EditInfosActivity.class);
                startActivity(intentEditMail);
            }
        });
        deconnexionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop Up des conditions d'utilisations
                dialogDeco = new Dialog(ProfilActivity.this);
                dialogDeco.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogDeco.setContentView(R.layout.deconnexion_pop_up);
                dialogDeco.setCanceledOnTouchOutside(false);
                alertbox_quit_text = dialogDeco.findViewById(R.id.alertbox_quit_text);

                retourButton = dialogDeco.findViewById(R.id.retourButton);
                decoButton = dialogDeco.findViewById(R.id.decoButton);
                retourButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDeco.dismiss();
                    }
                });
                decoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        sharedPreferences.edit().remove(PREFS_PRENOM).apply();
                        sharedPreferences.edit().remove(PREFS_NOM).apply();
                        sharedPreferences.edit().remove(PREFS_MAIL).apply();
                        Intent intentDeco = new Intent(ProfilActivity.this, InscriptionActivity.class);
                        startActivity(intentDeco);
                    }
                });

                dialogDeco.show();
            }
        });
        aideTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * TODO : Afficher les règles du jeu
                 */
            }
        });

        retourImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AccueilActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        nomProfil = sharedPreferences.getString(PREFS_NOM, null);
        prenomProfil = sharedPreferences.getString(PREFS_PRENOM, null);
        mailProfil = sharedPreferences.getString(PREFS_MAIL, null);
        nomTextView.setText(prenomProfil + " " + nomProfil);
        mailTextView.setText(mailProfil);


        utilisateurReference.orderByChild("mail")
                .equalTo(mailProfil)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            retrieveMandat = data.child("mandat").getValue().toString();
                            retrieveCirconscription = data.child("circonscription").getValue().toString();
                            retrieveDepartement = data.child("departement").getValue().toString();
                            retrieveCommune = data.child("commune").getValue().toString();
                        }

                        mandatTextView.setText("Mandat : " + retrieveMandat + "\nCirconscription : " + retrieveCirconscription +
                                "\nDépartement : " + retrieveDepartement + "\nCommune : " + retrieveDepartement);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
