package com.fullsail.advancedviews_custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class GameContent {


    public static List<GameItem> ITEMS = new ArrayList<GameItem>();
    public static Map<String, GameItem> ITEM_MAP = new HashMap<String, GameItem>();

    static {
        // Add 3 sample items.
        addItem(new GameItem("1", "Final Fantasy 6", "Squaresoft", "April 2, 1994", "Yoshinori Kitase", "Nobuo Uematsu"));
        addItem(new GameItem("2", "Chrono Trigger", "Squaresoft", "March 11, 1995", "Takashi Tokita", "Yasnuroi Mitsuda"));
        addItem(new GameItem("3", "Secret of Evermore", "Squaresoft", "October 1, 1995", "Alan Weiss", "Jeremy Soule"));
    }

    private static void addItem(GameItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * Game Item that inputs id, title, developer, release date, director, and composer of the game
     */
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
            return title;
        }
    }
}
