package com.streamliners.sqldb.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.streamliners.sqldb.models.Item;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Sql Database";
    private static final String TABLE_NAME = "Items";
    private static final String KEY_LABEL = "Label";
    private static final String KEY_COLOR = "Color";
    private static final String KEY_IMAGE_URL = "ImageUrl";

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_LABEL + " TEXT, " +
                KEY_COLOR + " INTEGER, " +
                KEY_IMAGE_URL + " TEXT " + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public void addItem (Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_LABEL, item.label);
        values.put(KEY_COLOR, item.color);
        values.put(KEY_IMAGE_URL, item.imageUrl);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Item> fetchItems() {
        SQLiteDatabase db = getReadableDatabase();
        List<Item> result = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item(
                        cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getString(2)
                );
                result.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return result;
    }

    public void clearAllItems() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;

        db.execSQL(query);
        db.close();
    }
}
