package com.streamliners.sqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.streamliners.sqldb.helpers.databaseHelper;
import com.streamliners.sqldb.models.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private databaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new databaseHelper(this);
        addItems();
        fetchItems();
    }

    private void fetchItems() {
        List<Item> items = dbHelper.fetchItems();
        for (Item item : items) {
            Log.e("Items", item.label + "\t" + item.color + "\t" + item.imageUrl);
        }
    }

    private void addItems() {
        dbHelper.addItem(new Item("Mountain", 0, "image0.com"));
        dbHelper.addItem(new Item("Lake", 1, "image1.com"));
        dbHelper.addItem(new Item("Forest", 2, "image2.com"));
        dbHelper.addItem(new Item("Building", 3, "image3.com"));
        dbHelper.addItem(new Item("Sky", 4, "image4.com"));
        dbHelper.addItem(new Item("Earth", 5, "image5.com"));
    }
}