package com.example.myrecipesv1.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrecipesv1.R;
import com.example.myrecipesv1.RecycleResults;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    String data1[];
    Context context;

    public MyAdaptor(Context cx, String[] names){
        context=cx;
        data1=names;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptor.MyViewHolder holder, int position) {
        holder.name.setText(data1[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecycleResults.class);
                intent.putExtra("Name",data1[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.recipe_name);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
