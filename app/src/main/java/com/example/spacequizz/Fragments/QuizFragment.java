package com.example.spacequizz.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import com.google.firebase.database.ValueEventListener;


public class QuizFragment extends Fragment {
    TextView test,test1 ;
    DatabaseReference reference1;
    FirebaseAuth mAuth;
    FirebaseUser user ;
    String niveau ;
    View root ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  root = inflater.inflate(R.layout.fragment_quiz, container, false);
        test1=root.findViewById(R.id.level);
        mAuth= FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        reference1= FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                test1.setText(snapshot.child("niveau").getValue().toString());
                niveau = test1.getText().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        test = root.findViewById(R.id.commencer_quiz);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuestionsActivity.class);
                intent.putExtra("level",niveau);
                startActivity(intent);



            }
        });


        return  root ;
    }


}