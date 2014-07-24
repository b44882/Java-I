package com.fullsail.advancedviews_custom;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends Activity{

    //TextViews
    private TextView gameTitle;
    private TextView gameDeveloper;
    private TextView gameRelease;
    private TextView gameDirector;
    private TextView gameComposer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Bundle extras = getIntent().getExtras();

        gameTitle = (TextView) findViewById(R.id.game_name);
        gameDeveloper = (TextView) findViewById(R.id.game_developer);
        gameRelease = (TextView) findViewById(R.id.game_year);
        gameDirector = (TextView) findViewById(R.id.game_director);
        gameComposer = (TextView) findViewById(R.id.game_composer);

        gameTitle.setText("Title: " + extras.getString("game_title"));
        gameDeveloper.setText("Developer: " + extras.getString("game_developer"));
        gameRelease.setText("Release: " + extras.getString("game_release"));
        gameDirector.setText("Director: " + extras.getString("game_director"));
        gameComposer.setText("Composer: " + extras.getString("game_composer"));

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
