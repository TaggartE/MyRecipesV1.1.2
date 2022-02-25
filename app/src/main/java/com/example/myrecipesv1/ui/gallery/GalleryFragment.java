package com.example.myrecipesv1.ui.gallery;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myrecipesv1.R;
import com.example.myrecipesv1.databinding.FragmentGalleryBinding;
import com.example.myrecipesv1.ui.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    public GalleryViewModel galleryViewModel;
    public FragmentGalleryBinding binding;
    //create the variables needed for the process
    String name="";
    final int[] step = {1};
    final int[] ing = {1};
    final int[] stp = {1};
    List<String> myRecipeList = new ArrayList<String>();
    List<String> myStepList = new ArrayList<String>();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        startSteps(binding,root);

        //this is the beginninng of the recipe creation process
        Button nextButton = (Button) root.findViewById(R.id.nextButton);

        TextView tv = (TextView) root.findViewById(R.id.tv);
        EditText editText = (EditText) root.findViewById(R.id.primaryinput);


        //listener for button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do what you want on the press of 'done'
                System.out.println("made it to here");
                //first, check to see if there is a name within the editText
                //only if exittext is not null
                if(editText.getText().toString().isEmpty()) {
                    editText.setError("Cannot be null");
                    return;
                    //break out of statement so we do not increase the "stepper"
                }
                //end if statement
                //if we are on step one
                if(step[0]==1){
                    //log the name!

                    name=editText.getText().toString();
                    editText.setText("");
                }
                //since I want to make it so you do not have to proceed further I am making duplicate code (Dont tell)
                if(step[0]==2){
                    //we are adding ingredients still so we can append all changes in the edittext to the myRecipeList ArrayList
                    if(editText.getText().toString().isEmpty()){
                        //do nothing
                    }
                    else{
                        String whatWereAdding=editText.getText().toString();
                        myRecipeList.add(whatWereAdding);
                        ing[0]++;
                        editText.setText("");
                        startSteps(binding,root);
                    }
                }
                if(step[0]==3){
                    //we are adding steps to the step arraylist
                    if(editText.getText().toString().isEmpty()) {

                        //do nothing
                    }
                    else{
                        String stepwereadding=editText.getText().toString();
                        myStepList.add(stepwereadding);
                        stp[0]++;
                        editText.setText("");
                        startSteps(binding,root);
                    }
                }
                //proceed further into the code
                //VERY IMPORTANT STATEMENT RIGHT HERE
                step[0]++;
                //VERY IMPORTANT STATEMENT RIGHT HERE

                //Clear the editText
                editText.setText("");
                startSteps(binding,root);



                //END END END END END END

            }
        });
        //listener for done key
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if(editText.getText().toString().isEmpty())editText.setError("Cannot be null");

                    if(step[0]==2){
                        //we are adding ingredients still so we can append all changes in the edittext to the myRecipeList ArrayList
                        //only if exittext is not null
                        if(editText.getText().toString().isEmpty())editText.setError("Cannot be null");
                        else{
                            String whatWereAdding=editText.getText().toString();
                            myRecipeList.add(whatWereAdding);
                            ing[0]++;
                            editText.setText("");
                            startSteps(binding,root);
                        }

                    }
                    if(step[0]==3){
                        //we are adding steps to the step arraylist
                        //only if the edittext is not null

                        if(editText.getText().toString().isEmpty())editText.setError("Cannot be null");
                        else{
                            String stepwereadding=editText.getText().toString();
                            myStepList.add(stepwereadding);
                            stp[0]++;
                            editText.setText("");
                            startSteps(binding,root);
                        }

                    }
                }
                return false;
            }
        });








     return root;
    }


    public void startSteps(FragmentGalleryBinding binding,View root){

        Button nextButton = (Button) root.findViewById(R.id.nextButton);

        TextView tv = (TextView) root.findViewById(R.id.tv);
        EditText editText = (EditText) root.findViewById(R.id.primaryinput);
        if(step[0]==1){//do this
            System.out.println("Im here");
            tv.setText("Name your Masterpiece");

        }
        if(step[0]==2){//do this
            //nextButton.setVisibility(View.VISIBLE);
            tv.setText("Ingredient "+ing[0]);

        }
        if(step[0]==3) {//do this
            tv.setText("Step " + stp[0]);
        }
        if(step[0]==4){
            //we wrap it all up and close the fragment

            DatabaseHandler dbh = new DatabaseHandler();
            System.out.println(myRecipeList);
            System.out.println(myStepList);
            String[] mrl = new String[myRecipeList.size()];
            for(int j =0;j<myRecipeList.size();j++){
                mrl[j] = myRecipeList.get(j);
            }
            String[] msl = new String[myStepList.size()];
            for(int j =0;j<myStepList.size();j++){
                msl[j] = myStepList.get(j);
            }

            //SUPER IMPORTANT STATEMENT HERE
            dbh.takeItFromHere(name,mrl,msl);
            //big databaseHandler Statement to handle the process

            System.out.println("This recipe has been logged");

            //simulate back arrow
            getActivity().onBackPressed();


        }
    }

    /*

     */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}