package com.example.myrecipesv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myrecipesv1.ui.DatabaseHandler;
import com.example.myrecipesv1.ui.MyAdaptor;

import java.util.Arrays;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView= findViewById(R.id.recyclerView);

        //we are going to get the array for our recipe NAMES
        DatabaseHandler dbh = new DatabaseHandler();
        String[] names = dbh.getNames();
        System.out.println(Arrays.toString(names));
        System.out.println(names.length);

        MyAdaptor myAdaptor = new MyAdaptor(this,names);
        recyclerView.setAdapter(myAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}