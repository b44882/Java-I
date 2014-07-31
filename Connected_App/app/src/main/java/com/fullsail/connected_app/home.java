package com.fullsail.connected_app;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class home extends Activity {

    private TextView errorTextView;
    ProgressBar loading;
    Boolean connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ConnectivityManager mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        setContentView(R.layout.activity_home);
        loading = (ProgressBar) findViewById(R.id.progressBar);
        errorTextView = (TextView) findViewById(R.id.errorTextview);
        checkConnectivity(mgr);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //Button selected
                checkConnectivity(mgr);
                if (connection == true){
                    TextView searchEditText = (TextView) findViewById (R.id.searchTextField);
                    String symbol = searchEditText.getText().toString();
                    try{
                        String urlString = "https://gdata.youtube.com/feeds/api/videos?q=" + symbol + "&orderby=published&start-index=11&max-results=5&v=2&alt=json";
                        URL queryURL = new URL(urlString);
                        new GetSearchTask().execute(queryURL);

                    } catch (Exception e) {

                    }
                } else {

                }
            }
        });


    }

    public void checkConnectivity (ConnectivityManager manager){
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()){
            errorTextView.setText("Connection Established");
            connection = true;
        } else {
            errorTextView.setText("Error: No Connection!  Please check network connection.");
            connection = false;
        }

    }

    private void updateDisplay(){

    }

    private class GetSearchTask extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "Java 1 Week 4:";


        @Override

        protected void onPreExecute(){
            loading.setVisibility(View.VISIBLE);
        }
        protected JSONObject doInBackground(URL... urls) {
            loading.setVisibility(View.VISIBLE);
            String jsonString = "";
            for(URL queryURL : urls){
                try{
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                } catch (Exception e){
                    Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());
                    return null;
                }
            }

            Log.i(TAG, "Received Data: " + jsonString);

            JSONObject apiData;

            try{
                apiData = new JSONObject(jsonString);
            } catch (Exception e){
                Log.e(TAG, "Cannot convert API response to JSON");
                apiData = null;
            }

            try{
                apiData = (apiData != null) ? apiData.getJSONObject("feed") : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e){
                Log.e(TAG, "Could not parse data record from response: " + apiData.toString());
                apiData = null;
            }
            return apiData;
        }
        

        protected void onPostExecute(JSONObject apiData){
            loading.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
