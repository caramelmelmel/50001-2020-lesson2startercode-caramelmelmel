package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.logging.LogRecord;
import android.app.ProgressDialog;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfA;
    EditText editTextSubValueOfB;
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String A_KEY = "A_KEY";
    public final static String B_KEY = "B_KEY";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget

        //TODO 3.5 Get references to the editText widgets
        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);

        //TODO 3.6 Get a reference to the Back To Calculator Button
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        //TODO 3.7 Set up setOnClickListener
        buttonBackToCalculator.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ValidVal();
                Illarg();
                String home = editTextSubValueOfA.getText().toString();
                String foreign = editTextSubValueOfB.getText().toString();
                Intent intent = new Intent(SubActivity.this,MainActivity.class);
                //passing the values back to main activity
                intent.putExtra(A_KEY,home);
                intent.putExtra(B_KEY,foreign);

            }

        });
        //TODO 3.8 Obtain the values stored in the editTextWidgets
        editTextSubValueOfA.getText();
        editTextSubValueOfB.getText();
        //TODO 3.9 Check that these values are valid --> see Utils class

        //TODO 3.10 Set up an explicit intent and pass the exchange rate back to MainActivity
        //TODO 3.11 Decide how you are going to handle a divide-by-zero situation or when negative numbers are entered
        //TODO 3.12 Decide how you are going to handle a situation when the editText widgets are empty

    }
    //catches illegal argument exception
    private void ValidVal(){
        String A = editTextSubValueOfA.getText().toString();
        String B = editTextSubValueOfB.getText().toString();
        if(A.isEmpty()){
            editTextSubValueOfA.setError("Enter a value please");
            return;

        }
        else if(B.isEmpty()){
            editTextSubValueOfB.setError("Enter a value please");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

    }

    private void Illarg(){
        String A = editTextSubValueOfA.getText().toString();
        String B = editTextSubValueOfB.getText().toString();
        Double AtoDouble = new Double(A);
        Double BtoDouble = new Double(B);
        if(AtoDouble<0){
            editTextSubValueOfA.setError("Please enter a positive value of A");
            return;
        }
        else if(BtoDouble<0){
            editTextSubValueOfB.setError("Please enter a positive value of B");
            return;
        }
    }

    //TODO 4.10 Don't forget to override onPause()

}
