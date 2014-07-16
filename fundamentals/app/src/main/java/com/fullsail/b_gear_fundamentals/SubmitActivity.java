package com.fullsail.b_gear_fundamentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

import java.util.ArrayList;
import java.util.HashSet;


public class SubmitActivity extends Activity {

    HashSet<String> submitSet = new HashSet<String>();

    String countString;
    String averageString;
    final String TAG = "Submit Activity Demo";

    Integer average;

    private TextView submitEditText;
    private TextView countTextView;
    private TextView averageTextView;

    private ListView submitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicactivity);

        //TextViews
        submitEditText = (TextView) findViewById(R.id.submitEditText);
        countTextView = (TextView) findViewById(R.id.countTextView);
        averageTextView = (TextView) findViewById(R.id.averageTextView);
        submitListView = (ListView) findViewById(R.id.submitListView);

        //Buttons
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //Button selected
                calculateResult(String.valueOf(submitEditText.getText()));
            }
        });

        //ListView
        submitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //ListView Item Selected
              Log.i(TAG, "List Item Selected");
              TextView selected = (TextView) view;
              showAlert(String.valueOf(selected.getText()));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.basicactivity, menu);
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
    public void calculateResult(String result)  //Calculates results after Submit Button is selected
    {
        submitSet.add(result);  //Adds result into a set

        countString = String.valueOf(submitSet.size());  //Counts the size of the set
        countTextView.setText("Set Count:" + countString);              //Displays count

        addList(submitSet);                              //Method that updates list from set

        average = averageLength(submitSet);              //Integer Value that contains average of word length from set
        averageString = String.valueOf(average);         //Converts Average Integer into a String
        averageTextView.setText("Average Length:" + averageString);          //Displays Average word length of set

        showToast(result, "was added.");

        submitEditText.setText("");                      //Resets text back to blank.
    }
    public Integer averageLength(HashSet<String> setList) //Returns average length of User Submitted Items
    {
        ArrayList<String> setToList = new ArrayList<String>(setList);  //Converts set to array for easier conversion
        Integer length = 0; //Resets length to 0
        String item;        //Creates item string.
        for (int i = 0; i < setList.size(); i++) //For loop that goes through size of set
        {
            item = setToList.get(i);             //Adds item to converted set
            length += item.length();             //Adds onto on going length integer
        }
        length = length / setList.size();       //Gets average by dividing the sum of all letters of every string in the set by the length of the set
        return length;                          //Returns the average length
    }
    public void addList(HashSet<String> setList)  //Adds items to the listview
    {
        ArrayList<String> setToList = new ArrayList<String>(setList);  //Converts set to array for easier conversion
        ArrayAdapter<String>submitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,setToList);  //Creates local adapter to apply to the listview

        submitAdapter.add("testadd");
        submitListView.setAdapter(submitAdapter); //Applies adapter to the listview to display results
    }

    public void showAlert (String alert) //Method that displays alert box
    {
        new AlertDialog.Builder(this)
                .setTitle("Display List Item")
                .setMessage(alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setIcon(android.R.drawable.sym_def_app_icon)
                .show();
    }
    public void showToast(String item, String action)
    {
        Context context = getApplicationContext();
        CharSequence text = item + " " + action;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);

        toast.show();

    }
}
