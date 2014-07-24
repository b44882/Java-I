package com.fullsail.advancedviews_custom;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyActivity extends Activity {

    Spinner gameSpinner;
    ListView gameListView;
    ArrayAdapter<GameItem> gameAdapter;
    List<GameItem> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameList = new ArrayList<GameItem>();
        gameList.add(new GameItem("1", "Final Fantasy 6", "Squaresoft", "April 2, 1994", "Yoshinori Kitase", "Nobuo Uematsu"));
        gameList.add(new GameItem("2", "Chrono Trigger", "Squaresoft", "March 11, 1995", "Takashi Tokita", "Yasnuroi Mitsuda"));
        gameList.add(new GameItem("3", "Secret of Evermore", "Squaresoft", "October 1, 1995", "Alan Weiss", "Jeremy Soule"));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_my_land);
            gameAdapter = new ArrayAdapter<GameItem>(this,android.R.layout.simple_list_item_1,gameList);
            gameListView = (ListView) findViewById(R.id.game_listview);
            gameListView.setAdapter(gameAdapter);

        } else {
            setContentView(R.layout.activity_my);
            gameAdapter = new ArrayAdapter<GameItem>(this,android.R.layout.simple_spinner_dropdown_item,gameList);
            gameSpinner = (Spinner) findViewById(R.id.game_spinner);
            gameSpinner.setAdapter(gameAdapter);


        }
    }

    public static class GameItem {
        public String id;
        public String title;
        public String developer;
        public String release;
        public String director;
        public String composer;

        public GameItem(String id, String title, String developer, String release, String director, String composer) {
            this.id = id;
            this.title = title;
            this.developer = developer;
            this.release = release;
            this.director = director;
            this.composer = composer;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view, int post, long id){

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
