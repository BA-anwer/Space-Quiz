 package com.example.spacequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import model.User;

 public class QuestionsActivity extends AppCompatActivity {
    TextView mQuestions, mOption1, mOption2, mOption3, verif_question, timer, nbre_question;
     int total = 0;
    int correct = 0;
    int wrong = 0;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        mQuestions = findViewById(R.id.question_text);
        mOption1 = findViewById(R.id.option1);
        mOption2 = findViewById(R.id.option2);
        mOption3 = findViewById(R.id.option3);
        verif_question = findViewById(R.id.verif_text);
        timer = findViewById(R.id.timerTexte);
        nbre_question = findViewById(R.id.nbre_questions);

        UpdateQuestion();
        reserveTimer(60, timer);








    }

    private void UpdateQuestion() {
        total++;
        nbre_question.setText(String.valueOf(total));
        if (total == 11) {
            Intent i = new Intent(QuestionsActivity.this, ResultActivity.class);
            i.putExtra("result", String.valueOf((correct * 10)));
            i.putExtra("correct", String.valueOf(correct));
            i.putExtra("wrong", String.valueOf(wrong));
            startActivity(i);
            finish();
        } else {
            //Quiz questions
            reference = FirebaseDatabase.getInstance().getReference().child("Quizz").child(getIntent().getStringExtra("level")).child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    mQuestions.setText(snapshot.child("question").getValue().toString());
                    mOption1.setText(snapshot.child("option1").getValue().toString());
                    mOption2.setText(snapshot.child("option2").getValue().toString());
                    mOption3.setText(snapshot.child("option3").getValue().toString());

                    mOption1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOption1.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                mOption1.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                verif_question.setText("Vrai");
                                verif_question.setTextColor(getResources().getColor(R.color.vrai));
                                correct++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verif_question.setText("");
                                        mOption1.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            } else {
                                verif_question.setText("Faux");
                                verif_question.setTextColor(getResources().getColor(R.color.faux));
                                wrong++;
                                mOption1.setBackground(getResources().getDrawable(R.drawable.wrong_question));

                                if (mOption2.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                    mOption2.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                } else if (mOption3.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                    mOption3.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verif_question.setText("");
                                        mOption1.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        mOption2.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        mOption3.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        UpdateQuestion();
                                    }
                                }, 1500);


                            }
                        }
                    });
                    mOption2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOption2.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                mOption2.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                verif_question.setText("Vrai");
                                verif_question.setTextColor(getResources().getColor(R.color.vrai));
                                correct++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verif_question.setText("");

                                        mOption2.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            } else {
                                verif_question.setText("Faux");
                                verif_question.setTextColor(getResources().getColor(R.color.faux));
                                wrong++;
                                mOption2.setBackground(getResources().getDrawable(R.drawable.wrong_question));

                                if (mOption1.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                    mOption1.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                } else if (mOption3.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                    mOption3.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verif_question.setText("");
                                        mOption1.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        mOption2.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        mOption3.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        UpdateQuestion();
                                    }
                                }, 1500);


                            }
                        }
                    });
                    mOption3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOption3.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                mOption3.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                verif_question.setText("Vrai");
                                verif_question.setTextColor(getResources().getColor(R.color.vrai));
                                correct++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verif_question.setText("");
                                        mOption3.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            } else {
                                verif_question.setText("Faux");
                                verif_question.setTextColor(getResources().getColor(R.color.faux));
                                wrong++;
                                mOption3.setBackground(getResources().getDrawable(R.drawable.wrong_question));

                                if (mOption1.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                    mOption1.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                } else if (mOption2.getText().toString().equals(snapshot.child("answer").getValue().toString())) {
                                    mOption2.setBackground(getResources().getDrawable(R.drawable.correct_question));
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verif_question.setText("");
                                        mOption1.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        mOption2.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        mOption3.setBackground(getResources().getDrawable(R.drawable.score_bg));
                                        UpdateQuestion();
                                    }
                                }, 1500);


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
    public void reserveTimer(int seconde, final TextView timer) {
        new CountDownTimer(seconde * 1000 + 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconde = (int) (millisUntilFinished / 1000);
                int min = seconde / 60;
                seconde = seconde % 60;
                timer.setText(String.format("%02d", min) + ":" + String.format("%02d", seconde));

            }

            @Override
            public void onFinish() {
                if (total > 11) {
                    Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
                    intent.putExtra("result", String.valueOf((correct * 10)));
                    intent.putExtra("correct", String.valueOf(correct));
                   intent.putExtra("wrong", String.valueOf(wrong));
                    startActivity(intent);
                    finish();
                }

            }
        }.start();
    }


 }