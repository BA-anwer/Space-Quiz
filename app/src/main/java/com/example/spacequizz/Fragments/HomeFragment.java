package com.example.spacequizz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spacequizz.Adapter.MyAdapter;
import com.example.spacequizz.R;


public class HomeFragment extends Fragment {
    String title[],resume[];
    int images[]={R.drawable.planet,R.drawable.mercure,R.drawable.venus,R.drawable.mars,R.drawable.jupiter,R.drawable.saturne,R.drawable.uranus,R.drawable.neptune,R.drawable.pluton};
    RecyclerView recyclerView ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        title=getResources().getStringArray(R.array.planete_name);
        resume=getResources().getStringArray(R.array.resume);

        MyAdapter myAdapter = new MyAdapter(getContext(),title,resume,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));









        return root ;
    }
}