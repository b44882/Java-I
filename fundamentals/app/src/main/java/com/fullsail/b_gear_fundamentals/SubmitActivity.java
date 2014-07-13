package com.fullsail.b_gear_fundamentals;

import android.app.Activity;
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
    private TextView resultTextView;
    private TextView countTextView;
    private TextView averageTextView;

    private ListView submitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicactivity);

        //TextViews
        submitEditText = (TextView) findViewById(R.id.submitEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        countTextView = (TextView) findViewById(R.id.countTextView);
        averageTextView = (TextView) findViewById(R.id.averageTextView);
        submitListView = (ListView) findViewById(R.id.submitListView);

        //Buttons
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult(String.valueOf(submitEditText.getText()));
            }
        });

        //ListView
        submitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Log.i(TAG, "List Item Selected");
              TextView selected = (TextView) view;
              showResult(String.valueOf(selected.getText()));
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
    public void calculateResult(String result)
    {
        submitSet.add(result);
        showResult(result);

        countString = String.valueOf(submitSet.size());
        countTextView.setText(countString);

        addList(submitSet);

        average = averageLength(submitSet);
        averageString = String.valueOf(average);
        averageTextView.setText(averageString);
    }
    public Integer averageLength(HashSet<String> setList)
    {
        ArrayList<String> setToList = new ArrayList<String>(setList);
        Integer length = 0;
        String item;
        for (int i = 0; i < setList.size(); i++)
        {
            item = setToList.get(i);
            length += item.length();
        }
        length = length / setList.size();
        return length;
    }
    public void addList(HashSet<String> setList)
    {
        ArrayList<String> setToList = new ArrayList<String>(setList);
        ArrayAdapter<String>submitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,setToList);
        submitListView.setAdapter(submitAdapter);
    }
    public void showResult(String result)
    {
        resultTextView.setText(result);
    }
}
