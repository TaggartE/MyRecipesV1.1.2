package com.example.myrecipesv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.myrecipesv1.ui.DatabaseHandler;

public class RecycleResults extends AppCompatActivity {

    String Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_results);

        getData();
        setData();

    }

    private void getData(){
        if(getIntent().hasExtra("Name")){
            Name= getIntent().getStringExtra("Name");
        }
    }
    private void setData(){
        DatabaseHandler dbh = new DatabaseHandler();
        String[] ingredients=dbh.getIngredients(Name);
        String[] steps=dbh.getSteps(Name);

        boolean ingnull=false;
        boolean stepnull=false;
        if(ingredients==null){
            ingnull=true;
        }
        if(steps==null){
            stepnull=true;
        }

        String ingDisplay="";
        String stepDisplay="";


        if(!ingnull){
            for(int i = 0; i<ingredients.length;i++){
                ingDisplay+=i+1+". "+ingredients[i];
                ingDisplay+="\n";
            }
        }
        else{
            ingDisplay="No Ingredients to Display";
        }

        if(!stepnull){
            for(int i = 0; i<steps.length;i++){
                stepDisplay+=i+1+". "+steps[i];
                stepDisplay+="\n";
            }
        }
        else{
            stepDisplay="No Steps to Display";
        }

        TextView IngView = (TextView) findViewById(R.id.ingredients);
        TextView StepView = (TextView) findViewById(R.id.steps);

        IngView.setText(ingDisplay);
        StepView.setText(stepDisplay);

        IngView.setMovementMethod(new ScrollingMovementMethod());
        StepView.setMovementMethod(new ScrollingMovementMethod());

    }

}