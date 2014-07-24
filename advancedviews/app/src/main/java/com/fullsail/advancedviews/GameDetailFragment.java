package com.fullsail.advancedviews;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.fullsail.advancedviews.game.GameContent;


public class GameDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The game content this fragment is presenting.
     */
    private GameContent.GameItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GameDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = GameContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        // Show fragments of items in multiple text views.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.game_title)).setText(mItem.title);
            ((TextView) rootView.findViewById(R.id.game_developer)).setText(mItem.developer);
            ((TextView) rootView.findViewById(R.id.game_date)).setText(mItem.release);
            ((TextView) rootView.findViewById(R.id.game_director)).setText(mItem.director);
            ((TextView) rootView.findViewById(R.id.game_composer)).setText(mItem.composer);
        }

        return rootView;
    }
}
