package com.example.spacequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AideActivity extends AppCompatActivity {
    ImageView mImage_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);

        //AideActivity --------------> LoginActivity
        mImage_back=findViewById(R.id.image_back);
        mImage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AideActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}