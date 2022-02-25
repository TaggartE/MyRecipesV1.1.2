package com.example.myrecipesv1.ui;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.view.View;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler {

    final String TABLE_1_NAME="NAMES";


    //2021 Taggart Elliott
    //com.example.myrecipesv1.ui.DatabaseHandler handles inputs from Log Fragment so that they can be retrieved in later fragments

    private String name;


    public void takeItFromHere(String name, String[]ingredients, String[]steps){

        //absolutely massive try catch to process all the data
        try{

            //take the raw String name and give it a code at the end so the same name can be used multiple times
            //genereate random 5 digit number to the front of the name. This can be removed with substring method.

            //end

            //open database
            SQLiteDatabase myDB = SQLiteDatabase.openOrCreateDatabase(
                    "/data/data/com.example.myrecipesv1/myrecipesdatabase.sqlite",
                    null);


            //create table if its the first time for the names of the recipes
            myDB.execSQL("CREATE TABLE IF NOT EXISTS '"+TABLE_1_NAME+"' (" +
                    "name TEXT)");

            //insert the recipe into the table
            myDB.execSQL("INSERT INTO '"+TABLE_1_NAME+"'(name) VALUES('"+name+"')");

            //create name for the second table that is only for the name of said recipe
            String Table_2_name=name.replaceAll("\\s", "").toUpperCase()+"_INGREDIENTS";//ex: BANANA PIE = BANANAPIE_INGREDIENTS

            //create the table for this recipe
            myDB.execSQL("CREATE TABLE '"+Table_2_name+"' (Ingredients TEXT)");

            //we will insert into multiple times because the array may have multiple entries
            for(int i = 0; i<ingredients.length;i++){
                myDB.execSQL("INSERT INTO '"+Table_2_name+"'(Ingredients) VALUES('"+ingredients[i]+"')");
            }
            //this will iterate until the end of the array


            //now we can create the last table for this recipe which is the steps
            String Table_3_name=name.replaceAll("\\s", "").toUpperCase()+"_STEPS";//ex: BANANA PIE = BANANAPIE_STEPS

            //create the table for the steps
            myDB.execSQL("CREATE TABLE '"+Table_3_name+"' (Steps TEXT)");

            //for loop to insert all data into the table for the related recipe
            for(int i = 0; i<steps.length;i++){
                myDB.execSQL("INSERT INTO '"+Table_3_name+"'(Steps) VALUES('"+steps[i]+"')");
            }
            //end loop

            //close the database
            myDB.close();
            //logs in console to confirm database has been logged
            System.out.println("You made it to the end of the database logging!");

            //end try
        }catch(SQLException e){
            //if something somehow doesn't add up, print the stack trace
            e.printStackTrace();
        }
        //end the catch
    }
    //end takeItFromHere

    public String[] getNames(){
        /*
        getNames

         */
        //create string arrayList to parse into array
        List<String> toreturn = new ArrayList<String>();
        //open database
        SQLiteDatabase myDB = SQLiteDatabase.openOrCreateDatabase(
                "/data/data/com.example.myrecipesv1/myrecipesdatabase.sqlite",
                null);

        //database is opened run querey
        Cursor crs = myDB.rawQuery("SELECT * FROM '"+TABLE_1_NAME+"';",null);

        if (crs.moveToFirst()) {
            //database has at least one record
            do {
                toreturn.add(crs.getString(0));

            } while (crs.moveToNext());
        }
        else return null;

            //create string array to return
            String[] returnme=new String[toreturn.size()];
            for(int i = 0; i<toreturn.size();i++){
                returnme[i]=toreturn.get(i);
            }

            myDB.close();

            return returnme;


    }
    public String[] getIngredients(String name){
        //create string arrayList to parse into array
        List<String> toreturn = new ArrayList<String>();
        //create table name
        String tableName=name.replaceAll("\\s", "").toUpperCase()+"_INGREDIENTS";
        //open database
        SQLiteDatabase myDB = SQLiteDatabase.openOrCreateDatabase(
                "/data/data/com.example.myrecipesv1/myrecipesdatabase.sqlite",
                null);

        //database is opened run querey
        Cursor crs = myDB.rawQuery("SELECT * FROM '"+tableName+"';",null);

        if (crs.moveToFirst()) {
            //database has at least one record
            do {
                toreturn.add(crs.getString(0));

            } while (crs.moveToNext());
        }
        else return null;

        //create string array to return
        String[] returnme=new String[toreturn.size()];
        for(int i = 0; i<toreturn.size();i++){
            returnme[i]=toreturn.get(i);
        }

        myDB.close();

        return returnme;


    }
    public String[] getSteps(String name){
        //create string arrayList to parse into array
        List<String> toreturn = new ArrayList<String>();
        //create table name
        String tableName=name.replaceAll("\\s", "").toUpperCase()+"_STEPS";
        //open database
        SQLiteDatabase myDB = SQLiteDatabase.openOrCreateDatabase(
                "/data/data/com.example.myrecipesv1/myrecipesdatabase.sqlite",
                null);

        //database is opened run querey
        Cursor crs = myDB.rawQuery("SELECT * FROM '"+tableName+"';",null);

        if (crs.moveToFirst()) {
            //database has at least one record
            do {
                toreturn.add(crs.getString(0));

            } while (crs.moveToNext());
        }
        else return null;

        //create string array to return
        String[] returnme=new String[toreturn.size()];
        for(int i = 0; i<toreturn.size();i++){
            returnme[i]=toreturn.get(i);
        }

        myDB.close();

        return returnme;


    }
}
