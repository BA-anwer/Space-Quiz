package com.example.spacequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.spacequizz.Fragments.QuizFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {
    TextView mResultat , mCorrect ,mWrong , mRetour ;
    FirebaseAuth mAuth ;
    DatabaseReference reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResultat=findViewById(R.id.resultat);
        mCorrect=findViewById(R.id.correct_nbre);
        mWrong=findViewById(R.id.ncorrect_nbtre);
        mRetour=findViewById(R.id.retour_text);

        Intent intent = getIntent();
        mResultat.setText(intent.getStringExtra("result")+"%");
        mCorrect.setText(intent.getStringExtra("correct"));
        mWrong.setText(intent.getStringExtra("wrong"));


        mRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //save score firebase
        mAuth= FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());
        reference.child("score").setValue(intent.getStringExtra("result")+"%");








    }
}