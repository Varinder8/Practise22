package com.chicago;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {
DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initializing();

        // Binding Spinners
        Spinner spinnerColumns = (Spinner) findViewById(R.id.spinnerPaymentMode);
        dataBase = new DataBase(this);

        ArrayAdapter<String> columnsArrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataBase.GetColumns());
        columnsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColumns.setAdapter(columnsArrayAdapter);

        Spinner spinnerYears =(Spinner) findViewById(R.id.spinnerDuration);
        ArrayAdapter<String> yearsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataBase.GetDuration());
        yearsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYears.setAdapter(yearsArrayAdapter);

        /*// Retrieving Data
        try
        {
            Item item = Item.GetItem(1);
            Toast.makeText(this, "Item :- " + item.name, Toast.LENGTH_LONG).show();

            List<Years> yearsList = Years.Retrieve();

           *//* Spinner spinnerYears = (Spinner) findViewById(R.id.spinnerYears);
            ArrayAdapter<Years> adpater = new ArrayAdapter<Years>(this, android.R.layout.simple_spinner_item,yearsList);
            adpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerYears.setAdapter(adpater);*//*

            Toast.makeText(this ,String.valueOf(yearsList.size()) ,Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Exception :- "+ ex.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("Exception -----**********------------", ex.getMessage());
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void Initializing() {

        try {

            // Deleting Data
            /*Columns.Delete();
            Years.Delete();
            RecurringDeposit.Delete();*/

          /*  dataBase =new DataBase(this);
            dataBase.Delete();*/

            /*Toast.makeText(this, "Deleted successfully.", Toast.LENGTH_LONG).show();*/

            // Saving Data

            /*Columns.Save();
            Years.Save();
            RecurringDeposit.Save();
            Toast.makeText(this, "Saved successfully.", Toast.LENGTH_LONG).show();*/

            dataBase =new DataBase(this);
            dataBase.Delete();
            //Toast.makeText(this, "Deleted successfully.", Toast.LENGTH_LONG).show();

            dataBase.CreateAllTables();
            dataBase.InsertAllDeposits();
            //Toast.makeText(this, "Saved successfully.", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            Toast.makeText(this,"Exception :- "+ ex.getMessage(),Toast.LENGTH_LONG).show();
            Log.d("Exception -----********---- :- ",ex.getMessage());
        }

    }

    public void SearchRecords(View view) {
     try {
        Intent intent = new Intent(this, Result.class);
        Spinner spinnerpaymentMode = (Spinner) findViewById(R.id.spinnerPaymentMode);
        intent.putExtra("Mode", spinnerpaymentMode.getSelectedItem().toString());
        Spinner spinnerDuration = (Spinner) findViewById(R.id.spinnerDuration);
        intent.putExtra("Duration", spinnerDuration.getSelectedItem().toString());
        TextView textAmount = (TextView) findViewById(R.id.Amount);
        intent.putExtra("Amount", textAmount.getText().toString());
        startActivity(intent);
    }
        catch (Exception ex){
            Log.d("Eexception -----********----",ex.getMessage());
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
