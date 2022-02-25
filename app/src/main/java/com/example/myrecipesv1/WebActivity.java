package com.example.myrecipesv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        TextView tv = (TextView) findViewById(R.id.restxt);
        tv.setText("Hello and Welcome to My Recipe!\n" +
                "Here is a How-To Guide on Creating Your Masterpiece!\n\n" +
                "1. Name Your Creation\n\n" +
                "2. Press the Next Button to Proceed to Ingredients\n\n" +
                "3. Using the Done Button, List all Ingredients. Press Next to Proceed\n\n" +
                "4. Using the Done Button, List all Steps. Press Next to Proceed.\n\n" +
                "To Look at Previous Recipes, Use the View Recipes Button.\n\n" +
                "I Hope you Enjoy!\n\n" +
                "Chef Taggart");
    }
}