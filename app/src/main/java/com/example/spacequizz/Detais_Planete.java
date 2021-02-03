package com.example.spacequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class Detais_Planete extends AppCompatActivity {
    ImageView planete_image ;
    ImageView icon_back ;
    TextView planete_name ;
    TextView planete_des ;
    TextView textButtonAR ;
    TextView textBar;
    TextView des_text ;
    String name , des , textbtn ;
    TextToSpeech textToSpeech ;
    ImageView speech_btn ;
    int image ;
    int color ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais__planete);

        planete_image = findViewById(R.id.planete_image);
        planete_name= findViewById(R.id.planete_name);
        planete_des = findViewById(R.id.planete_des);
        textButtonAR = findViewById(R.id.button_text);
        icon_back= findViewById(R.id.image_back);
        textBar= findViewById(R.id.textView12);
        des_text = findViewById(R.id.textView13);
        speech_btn = findViewById(R.id.speech_btn);

        //Data recuperation
        name = getIntent().getStringExtra("planete_name");
        des= getIntent().getStringExtra("planete_description");
        image=getIntent().getIntExtra("planete_image",0);
        textbtn = "Visitez "+name+" en AR";


        // Change color
        GradientDrawable drawable =(GradientDrawable) textButtonAR.getBackground();
        switch (name){
            case "la Terre": {
             color = R.color.terreColor ;
            break;}

            case "Mercure": {
                color = R.color.MercureColor ;
                break;}

            case "Vénus": {
                color = R.color.VénusColor ;
                break;}

            case "Mars": {
                color = R.color.MarsColor ;
                break;}

            case "Jupiter": {
                color = R.color.JupiterColor ;
                break;}

            case "Saturne": {
                color = R.color.SaturneColor ;
                break;}

            case "Uranus": {
                color = R.color.UranusColor ;
                break;}

            case "Neptune": {
                color = R.color.NeptuneColor ;
                break;}

            case "Pluton": {
                color = R.color.PlutonColor ;
                break;}

        }

        textBar.setTextColor(getResources().getColor(color));
        des_text.setTextColor(getResources().getColor(color));
        drawable.setStroke(2,getResources().getColor(color));
        DrawableCompat.setTint(icon_back.getDrawable(), ContextCompat.getColor(getApplicationContext(),color));

        //Text description to speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.FRANCE);
                }
            }
        });
        speech_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = planete_des.getText().toString();
                int speech = textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        // Set data in Activity
        planete_name.setText(name);
        planete_des.setText(des);
        planete_image.setImageResource(image);
        textButtonAR.setText(textbtn);

        // back to Home Fragment
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
                finish();
            }
        });


        //AR Core planete
        textButtonAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detais_Planete.this, Ar_Activity.class);
                intent.putExtra("planete_name",name);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.stop();
    }
}