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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextView mInscrire_Link,mAide_Link ;
    EditText mEmail ;
    EditText mMotPasse ;
    Button mConnecter_btn ;
    ProgressBar progressBar ;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.inputEmail);
        mMotPasse = (EditText) findViewById(R.id.inputMotPasse);
        mConnecter_btn = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressBar2);


        // Connexion with firebase
        mConnecter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL_PATTERN = getString(R.string.pattren_email);
                String email = mEmail.getText().toString().trim();
                String  motPasse = mMotPasse.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(motPasse)){
                    Toast.makeText(LoginActivity.this,"Les champs ne peuvent pas etre vides !", Toast.LENGTH_SHORT).show();
                }
                else if ((!email.matches(EMAIL_PATTERN))){
                    Toast.makeText(LoginActivity.this,"Adresse Email est invalide !", Toast.LENGTH_SHORT).show();
                }
                else if (motPasse.length()<= 6){
                    Toast.makeText(LoginActivity.this,"La longeur de mot de passe doit supèrieur à 6 caractères !", Toast.LENGTH_SHORT).show();
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    Connexion (email , motPasse);
                }
            }
        });







        //LoginActivity ----------->RegisterActivity
        mInscrire_Link = findViewById(R.id.inscrire_link);
        mInscrire_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        //LoginActivity ----------->AideActivity
        mAide_Link=findViewById(R.id.aide_link);
        mAide_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,AideActivity.class));
                finish();
            }
        });





    }

    private void Connexion( String email, String motPasse) {

        mAuth.signInWithEmailAndPassword(email,motPasse).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this,"Authentification échouée !", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}