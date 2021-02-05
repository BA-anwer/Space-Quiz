package com.example.spacequizz.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.spacequizz.LoginActivity;
import com.example.spacequizz.QuestionsActivity;
import com.example.spacequizz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfilFragment extends Fragment {
    TextView text_deconnexion ;
    TextView name , email , age,score ;
    FirebaseAuth mAuth ;
    FirebaseUser user;
    DatabaseReference reference ;
    RadioButton radioButton1,radioButton2,radioButton3 ;
    String level ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        setRetainInstance (true);

        //Logout
        text_deconnexion = root.findViewById(R.id.text_deconnexion);
        text_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth= FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent =   new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        //User info



        name=root.findViewById(R.id.text_name);
        email=root.findViewById(R.id.text_email);
        age=root.findViewById(R.id.text_age);
        score=root.findViewById(R.id.score);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("users");
        Query query = reference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    name.setText(ds.child("name").getValue().toString());
                    email.setText(ds.child("email").getValue().toString());
                    age.setText(ds.child("age").getValue().toString());
                    score.setText(ds.child("score").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Level with SharedPreferences
        radioButton1=root.findViewById(R.id.niveau1);
        radioButton2=root.findViewById(R.id.niveau2);
        radioButton3=root.findViewById(R.id.niveau3);

        radioButton1.setChecked(update("niveau1"));
        radioButton2.setChecked(update("niveau2"));
        radioButton3.setChecked(update("niveau3"));
        reference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        reference.child("niveau").setValue(level);


        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean one_isChecked) {
            Save("niveau1",one_isChecked);
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean two_isChecked) {
                Save("niveau2",two_isChecked);
            }
        });
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean there_isChecked) {
                Save("niveau3",there_isChecked);

            }
        });

        return root ;
    }

    private void Save (String key , Boolean value){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Level",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();

    }
    private Boolean update(String key){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Level",Context.MODE_PRIVATE);
        Boolean etat=  sharedPreferences.getBoolean(key,false);
        if (key.equals("niveau1")&&(etat)){
            level="Debutant";

        }
        if (key.equals("niveau2")&&(etat)){
            level="Amateur";

        }
        if (key.equals("niveau3")&&(etat)){
            level="Avancé";

        }
        return etat ;
    }


    @Override
    public void onPause() {
        radioButton1.setChecked(update("niveau1"));
        radioButton2.setChecked(update("niveau2"));
        radioButton3.setChecked(update("niveau3"));
        reference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        reference.child("niveau").setValue(level);
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean one_isChecked) {
                Save("niveau1",one_isChecked);
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean two_isChecked) {
                Save("niveau2",two_isChecked);
            }
        });
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean there_isChecked) {
                Save("niveau3",there_isChecked);

            }
        });
        super.onPause();
    }
}