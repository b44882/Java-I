//Brett Gear
//Java 1407

package com.fullsail.connected_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class home extends Activity {

    private TextView errorTextView;
    private TextView titleTextView;
    private TextView channelTextView;
    private SmartImageView youtubeImage;
    Button searchButton;
    ProgressBar loading;
    Boolean connection;
    Boolean success;
    String resultMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ConnectivityManager mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        setContentView(R.layout.activity_home);
        loading = (ProgressBar) findViewById(R.id.progressBar);
        errorTextView = (TextView) findViewById(R.id.errorTextview);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        channelTextView = (TextView) findViewById(R.id.channelTextView);
        youtubeImage = (SmartImageView) findViewById(R.id.youtubeImage);
        checkConnectivity(mgr);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //Button selected
                checkConnectivity(mgr);
                if (connection == true){
                    TextView searchEditText = (TextView) findViewById (R.id.searchTextField);
                    String symbol = searchEditText.getText().toString();
                    symbol = symbol.replace(" ", "+");
                    try{
                        String urlString = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q=" + symbol + "&type=video&videoCaption=closedCaption&key=AIzaSyD0m9TrFUsKqIwYCCyoX3ERVlSYTWm-FZk";
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
            success = true;
            resultMessage= "Connection Established";
            connection = true;
        } else {
            success = false;
            resultMessage = "Error: No Connection!  Please check network connection.";
            connection = false;
        }
        updateResult();
    }

    private class GetSearchTask extends AsyncTask<URL, Integer, JSONObject> {

        @Override
        protected void onPreExecute(){
            loading.setVisibility(View.VISIBLE);
            searchButton.setEnabled(false);
        }
        protected JSONObject doInBackground(URL... urls) {
            loading.setVisibility(View.VISIBLE);
            String jsonString = "";
            for(URL queryURL : urls){
                try{
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                } catch (Exception e){
                    success = false;
                    resultMessage = "Could not establish URLConnection";
                    return null;
                }
            }
            JSONObject apiData;
            JSONArray apiDataArray;
            try{
                apiData = new JSONObject(jsonString);
            } catch (Exception e){
                success = false;
                resultMessage = "Cannot convert API response to JSON";
                apiData = null;
            }

            try{
                apiDataArray = (apiData!= null) ? apiData.getJSONArray("items") : null;
                apiData = (apiDataArray != null) ? apiDataArray.getJSONObject(0).getJSONObject("snippet") : null;
                success = true;
                resultMessage ="API JSON data received";
            } catch (Exception e){
                success = false;
                resultMessage = "Could not parse data record.";
                apiData = null;
            }
            return apiData;
        }


        protected void onPostExecute(JSONObject apiData){

            JSONObject youtubeImageObject;

            if (apiData != null)
            {
                try {
                    titleTextView.setText(apiData.getString("title"));
                    channelTextView.setText(apiData.getString("channelTitle"));
                    youtubeImageObject = (apiData!= null) ? apiData.getJSONObject("thumbnails").getJSONObject("default") : null;
                    youtubeImage.setImageUrl(youtubeImageObject.getString("url"));
                } catch (JSONException e) {
                    success = false;
                    resultMessage= "Error Setting Up Display";
                }

            }
            loading.setVisibility(View.INVISIBLE);
            searchButton.setEnabled(true);
            updateResult();
        }
    }

    public void updateResult (){
        if (success){
            errorTextView.setTextColor(Color.GREEN);
        } else {
            errorTextView.setTextColor(Color.RED);
        }
        errorTextView.setText(resultMessage);
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
