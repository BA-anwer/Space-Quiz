package com.example.spacequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Question;

public class QuestionsActivity extends AppCompatActivity {
    TextView mQuestions,mOption1 , mOption2,mOption3,verif_question,timer ;
    int total = 1;
    int correct=0;
    int wrong=0;
    DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        mQuestions=findViewById(R.id.question_text);
        mOption1=findViewById(R.id.option1);
        mOption2=findViewById(R.id.option2);
        mOption3=findViewById(R.id.option3);
        verif_question=findViewById(R.id.verif_text);
        timer=findViewById(R.id.timerTexte);

        UpdateQuestion();




    }

    private void UpdateQuestion() {
      if (total>8){

      }
      else {
          reference= FirebaseDatabase.getInstance().getReference().child("Quizz").child("Debutant").child(String.valueOf(total));
          reference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  Question question = snapshot.getValue(Question.class);

                  mQuestions.setText(question.getQuestion());
                  mOption1.setText(question.getOption1());
                  mOption2.setText(question.getOption2());
                  mOption3.setText(question.getOption3());


                  mOption1.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (mOption1.getText().toString().equals(question.getAnswer())){
                              Handler handler = new Handler();
                              handler.postDelayed(new Runnable() {
                                  @Override
                                  public void run() {
                                    correct++;

                                  }
                              },1500);
                          }
                      }
                  });


              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
      }
    }
}