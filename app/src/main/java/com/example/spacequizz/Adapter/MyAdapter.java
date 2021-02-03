package com.example.spacequizz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spacequizz.Detais_Planete;
import com.example.spacequizz.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context ;
    String data1[] , data2[] ;
    int imagePlanete[] ;

    public MyAdapter (Context context , String title[],String resume[],int image[]){
        this.context = context ;
        data1 = title;
        data2=resume ;
        imagePlanete=image;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_planete,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pl_title.setText(data1[position]);
        holder.pl_resume.setText(data2[position]);
        holder.pl_image.setImageResource(imagePlanete[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pl_title , pl_resume ;
        ImageView pl_image ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            pl_title = itemView.findViewById(R.id.planet_title);
            pl_resume=itemView.findViewById(R.id.planete_resume);
            pl_image = itemView.findViewById(R.id.image_planete);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, Detais_Planete.class);
            intent.putExtra("planete_name",data1[position]);
            intent.putExtra("planete_description",data2[position]);
            intent.putExtra("planete_image",imagePlanete[position]);
            context.startActivity(intent);



        }
    }
}
