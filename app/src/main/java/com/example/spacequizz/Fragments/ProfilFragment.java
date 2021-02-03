package com.example.spacequizz.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.spacequizz.LoginActivity;
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
    TextView name , email , age ;
    FirebaseAuth mAuth ;
    FirebaseUser user;
    DatabaseReference reference ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profil, container, false);

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

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










        return root ;
    }
}