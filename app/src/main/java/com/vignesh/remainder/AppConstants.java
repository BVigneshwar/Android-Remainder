package com.vignesh.remainder;

import java.util.HashMap;

public class AppConstants {
    public static int category_drawable_color_list[] = new int[]{R.drawable.round_red, R.drawable.round_pink, R.drawable.round_purple, R.drawable.round_blue, R.drawable.round_cyan, R.drawable.round_green, R.drawable.round_yellow, R.drawable.round_orange, R.drawable.round_brown, R.drawable.round_grey};
    public static String color_list[] = new String[]{"#c62828", "#f06292", "#ab47bc", "#1976d2", "#0097a7", "#43a047", "#ffeb3b", "#fb8c00", "#795548", "#9e9e9e"};
    public static HashMap<String, Integer> color_map = new HashMap<>();
    static {
        color_map.put("#c62828", R.drawable.round_red);
        color_map.put("#f06292", R.drawable.round_pink);
        color_map.put("#ab47bc", R.drawable.round_purple);
        color_map.put("#1976d2", R.drawable.round_blue);
        color_map.put("#0097a7", R.drawable.round_cyan);
        color_map.put("#43a047", R.drawable.round_green);
        color_map.put("#ffeb3b", R.drawable.round_yellow);
        color_map.put("#fb8c00", R.drawable.round_orange);
        color_map.put("#795548", R.drawable.round_brown);
        color_map.put("#9e9e9e", R.drawable.round_grey);
    }
    public static String shared_preference_key = "assistant_shared_preference";
    public static String notes_sort_by_preference = "notes_sort_by_preference";
}