package com.example.spacequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText mNamePrenom ;
    EditText mAge ;
    EditText mEmail ;
    EditText mMotPasse ;
    Button inscrire_btn ;
    ProgressBar progressBar ;
    TextView mConneter_Link ;

    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    DatabaseReference reference ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNamePrenom = (EditText) findViewById(R.id.inputName);
        mAge = (EditText) findViewById(R.id.inputAge);
        mEmail = (EditText) findViewById(R.id.inputEmail);
        mMotPasse = (EditText) findViewById(R.id.inputMotPasse);
        inscrire_btn = findViewById(R.id.button_inscrire);
        progressBar = findViewById(R.id.progressBar);




        //Inscription firebase
        inscrire_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL_PATTERN = getString(R.string.pattren_email);
                String name = mNamePrenom.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String  motPasse = mMotPasse.getText().toString().trim();

                 // Validation input data

                if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(age) || TextUtils.isEmpty(email) || TextUtils.isEmpty(motPasse)){
                    Toast.makeText(RegisterActivity.this,"Les champs ne peuvent pas etre vides !", Toast.LENGTH_SHORT).show();
                }
                else if ((!email.matches(EMAIL_PATTERN))){
                    Toast.makeText(RegisterActivity.this,"Adresse Email est invalide !", Toast.LENGTH_SHORT).show();
                }
               else if (motPasse.length()<= 6){
                    Toast.makeText(RegisterActivity.this,"La longeur de mot de passe doit supèrieur à 6 caractères !", Toast.LENGTH_SHORT).show();
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    Inscription (name , age , email , motPasse);
                }


            }
        });


        //RegisterActivity -------------->LoginActivity
        mConneter_Link=findViewById(R.id.connecter_link);
        mConneter_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    private void Inscription(String name, String age, String email, String motPasse) {
        mAuth.createUserWithEmailAndPassword(email, motPasse).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                    User user = new User(name,age,email,motPasse,"0%");
                    reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Votre compte enregistrè avec succès !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });


                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(RegisterActivity.this,"Vous ne pouvez pas vous inscrire avec cet e-mail ou ce mot de passe !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}


