package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.MathContext;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    Button buttonConvert;
    Button buttonSetExchangeRate;
    EditText editTextValue;
    TextView textViewResult;
    TextView textViewExchangeRate;
    double exchangeRate;
    public final String TAG = "Logcat";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String RATE_KEY = "Rate_Key";
    //add new attributes to instantiate the exchange rate
    ExchangeRate exchangeRatecal;
    String exchangeRateStored;

    //create the following two shared pref attributes for 4.8 and 4.9
    private SharedPreferences ETpref1;
    public static final String ET_key1 = "home";
    //private SharedPreferences ETpref2;
    //public static final String ET_key2 = "foreign";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 4.8 and 4.9 save to shared preferences for the EditText widgets
        //ETpref1 = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        //ETpref2 = getSharedPreferences(ET_key2,MODE_PRIVATE);
        //editTextValue.setText(ETpref1.getString(ET_key1,exchangeRatecal.calculateAmount("4.00").toString()));

        //TODO 4.5 Get a reference to the sharedPreferences object
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        //TODO 4.6 Retrieve the value using the key, and set a default when there is none
        String defaultValue = getResources().getString(R.string.default_exchange_rate);
        mPreferences.getString(RATE_KEY,defaultValue);

        //TODO 3.13 Get the intent, retrieve the values passed to it, and instantiate the ExchangeRate class
        Intent intentSubToMain = getIntent();
        String home = intentSubToMain.getStringExtra(SubActivity.A_KEY); //for the local val
        String foreign = intentSubToMain.getStringExtra(SubActivity.B_KEY); //for the foreign val

        //when the user first uses the app,set the default exchange rate is used
        if(home == null && foreign==null){
            exchangeRatecal = new ExchangeRate(exchangeRateStored);
        }
        else{
            //use the user's exchange rate
            exchangeRatecal = new ExchangeRate(home,foreign);
        }

        //TODO 3.13a See ExchangeRate class --->

        //ref to the widgets in the layout
        editTextValue= findViewById(R.id.editTextValue);
        buttonConvert= findViewById(R.id.buttonConvert);
        textViewExchangeRate= findViewById(R.id.textViewExchangeRate);

        //Do 3.2 here
        buttonSetExchangeRate = findViewById(R.id.buttonSetExchangeRate);

        //TODO 2.2 Assign a default exchange rate of 2.95 to the textView
        textViewExchangeRate.setText(exchangeRatecal.getExchangeRate().toString());

        //TODO 2.3 Set up setOnClickListener for the Convert Button
        buttonConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(editTextValue.getText().toString().isEmpty()){
                    //TODO 2.4 Display a Toast & Logcat message if the editTextValue widget contains an empty string
                    Toast.makeText(MainActivity.this,"Please fill in a value",Toast.LENGTH_LONG).show();
                    Log.i(TAG,"User entered empty string ded");
                }
                //TODO 2.5 If not, calculate the units of B with the exchange rate and display it
                String foreignVal = editTextValue.getText().toString();
                //TODO 4.11
                String result = exchangeRatecal.calculateAmount(foreignVal).round(new MathContext(String.valueOf(RoundingMode.HALF_EVEN))).toString();
                textViewResult.setText(result);
                }
            });

        //TODO 3.3 Set up setOnClickListener for the 3.2 on top
        buttonSetExchangeRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 3.4 Explicit intent for user to get to the subactivity
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //TODO 4.11 [Implement yourself] In MainActivity, when the home amount is calculated, it should be rounded to two decimal places.
    //TODO 4.1 Go to res/menu/menu_main.xml and add a menu item Set Exchange Rate
    //TODO 4.2 In onOptionsItemSelected, add a new if-statement and code accordingly

    //TODO 5.1 Go to res/menu/menu_main.xml and add a menu item Open Map App
    //TODO 5.2 In onOptionsItemSelected, add a new if-statement


    //TODO 4.3 override the methods in the Android Activity Lifecycle here
    //TODO 4.4 for each of them, write a suitable string to display in the Logcat

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onpause()");
        //TODO 4.7 In onPause, get a reference to the SharedPreferences.Editor object
        SharedPreferences.Editor edit = mPreferences.edit();
        //TODO 4.8 store the exchange rate using the putString method with a key
        edit.putString(RATE_KEY,exchangeRatecal.getExchangeRate().toString());
        edit.apply();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.menuSetExchangeRate){
            Intent intent = new Intent(this,SubActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.mapapp){
            //TODO 5.3 code the Uri object and set up the intent
            //specify the location of where you want the map app to be
            //String location = getString(R.string.default_location);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("geo").opaquePart("0.0").appendQueryParameter("q","Changi Village");
            Uri geolocation = builder.build();
            //implicit intent
            /*Specify the general action, in this case it is to view data, hence
                Intent.ACTION_VIEW is passed to the constructor*/
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geolocation);
            //check that the intent is able to be carried out before starting the activity
            if( intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }


        }

        return super.onOptionsItemSelected(item);
    }





}
