package com.example.spacequizz.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacequizz.LoginActivity;
import com.example.spacequizz.QuestionsActivity;
import com.example.spacequizz.R;


public class QuizFragment extends Fragment {
    TextView test ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  root = inflater.inflate(R.layout.fragment_quiz, container, false);
        test = root.findViewById(R.id.commencer_quiz);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QuestionsActivity.class));
            }
        });

        return  root ;
    }
}