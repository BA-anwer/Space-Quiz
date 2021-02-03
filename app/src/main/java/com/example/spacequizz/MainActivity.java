package com.example.spacequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Animation mAnim_logo ;
    ImageView mImage_logo ;
    private static int SPALSH_SCREEN = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
        else {

            //Animation logo
            mAnim_logo = AnimationUtils.loadAnimation(this,R.anim.animation_logo);
            mImage_logo = findViewById(R.id.logo);
            mImage_logo.setAnimation(mAnim_logo);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPALSH_SCREEN);

        }

    }
}