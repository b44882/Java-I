package com.fullsail.b_gear_fundamentals;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import java.util.HashSet;


public class SubmitActivity extends Activity {

    HashSet<String> submitSet = new HashSet<String>();

    String submitString;
    String countString;
    final String TAG = "Submit Activity Demo";

    private TextView submitEditText;
    private TextView resultTextView;
    private TextView countTextView;
    private TextView averageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicactivity);

        //TextViews
        submitEditText = (TextView) findViewById(R.id.submitEditText);
        resultTextView = (TextView) findViewById(R.id.restultTextView);
        countTextView = (TextView) findViewById(R.id.countTextView);

        //Buttons
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Button Clicked");
                submitString = String.valueOf(submitEditText.getText());

                submitSet.add(submitString);
                resultTextView.setText(submitString);

                countString = String.valueOf(submitSet.size());

                countTextView.setText(countString);


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
}
