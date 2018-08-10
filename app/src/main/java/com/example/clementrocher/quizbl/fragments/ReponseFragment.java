package com.example.clementrocher.quizbl.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;


public class ReponseFragment extends Fragment {

    private OnItemSelectedListener reponseListener;
    String frag_reponseUtilisateur;
    String frag_bonneReponse;

    Button questionSuivanteButton;
    TextView messageTextView;

    public ReponseFragment() {
        // Required empty public constructor
    }

    public interface OnItemSelectedListener {

        public void onSuivant();
    }
    public static ReponseFragment newInstance(String reponseUtilisateur,String bonneReponse) {
        ReponseFragment reponseFragment = new ReponseFragment();
        Bundle args = new Bundle();
        args.putString("reponseUtilisateur", reponseUtilisateur);
        args.putString("bonneReponse", bonneReponse);
        reponseFragment.setArguments(args);
        return reponseFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            this.reponseListener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frag_reponseUtilisateur = getArguments().getString("reponseUtilisateur");
        frag_bonneReponse = getArguments().getString("bonneReponse");
        return inflater.inflate(R.layout.fragment_reponse, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        questionSuivanteButton = view.findViewById(R.id.questionSuivanteButton);
        messageTextView = view.findViewById(R.id.messageTextView);

        /**
         * Affichage du message
         */
        if(frag_reponseUtilisateur==frag_bonneReponse){
            messageTextView.setText("Vous avez répondu : \n"+ frag_bonneReponse +"\nBravo ! C'est la bonne réponse ! ");
        }
        else if(frag_reponseUtilisateur=="Pas de réponse"){
            messageTextView.setText("Le temps est écoulé ! \n La bonne réponse était : \n"+frag_bonneReponse);
        }
        else messageTextView.setText("Vous avez répondu : \n"+frag_reponseUtilisateur +"\nMalheureusement, la bonne réponse était : \n"+ frag_bonneReponse);

        /**
         * Listener du bouton
         */
        questionSuivanteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(ReponseFragment.this).commit();
            }
        });
    }






    @Override
    public void onDetach() {
        super.onDetach();

    }


}
