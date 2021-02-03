package com.example.spacequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.spacequizz.Fragments.HomeFragment;
import com.example.spacequizz.Fragments.ProfilFragment;
import com.example.spacequizz.Fragments.QuizFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Bottom navbar with fragment
        chipNavigationBar = findViewById(R.id.bottom_navbar);
        chipNavigationBar.setItemSelected(R.id.acceuil,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottom_menu();
    }


    private void bottom_menu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null ;
                switch (i){
                    case R.id.acceuil :
                        fragment = new HomeFragment();

                        break;

                    case R.id.quiz :
                        fragment = new QuizFragment();
                        break;

                    case R.id.compte :
                        fragment = new ProfilFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });

    }
}