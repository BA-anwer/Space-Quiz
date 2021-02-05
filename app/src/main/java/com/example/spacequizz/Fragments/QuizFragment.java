package com.example.spacequizz.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacequizz.LoginActivity;
import com.example.spacequizz.QuestionsActivity;
import com.example.spacequizz.R;
import com.example.spacequizz.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class QuizFragment extends Fragment {
    TextView test ;
    String niveau="";
    RadioButton radioButton ;
    RadioGroup radioGroup;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  root = inflater.inflate(R.layout.fragment_quiz, container, false);
        radioGroup=(RadioGroup) root.findViewById(R.id.levelGroup);
        //radioButton1=root.findViewById(R.id.niveau1);
       // radioButton2=root.findViewById(R.id.niveau2);
        //radioButton3=root.findViewById(R.id.niveau3);

          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  radioButton=root.findViewById(checkedId);
                  switch (radioButton.getId()){

                      case R.id.niveau1 :{
                          niveau = "Debutant" ;
                          break;
                      }

                      case R.id.niveau2 :{
                          niveau = "Amateur" ;
                          break;
                      }

                      case R.id.niveau3 :{
                          niveau = "Avancé" ;
                          break;
                      }
                  }
              }
          });




        test = root.findViewById(R.id.commencer_quiz);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (niveau.equals("")){
                    Toast.makeText(getActivity(),"Il faut choisir un niveau de difficultè !", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getContext(), QuestionsActivity.class);
                    intent.putExtra("level",niveau);
                    startActivity(intent);
                }


            }
        });


        return  root ;
    }


}