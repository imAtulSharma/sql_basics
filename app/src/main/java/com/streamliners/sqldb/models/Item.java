package com.streamliners.sqldb.models;

public class Item {
    public final String label;
    public final int color;
    public final String imageUrl;

    public Item(String label, int color, String imageUrl) {
        this.label = label;
        this.color = color;
        this.imageUrl = imageUrl;
    }
}
