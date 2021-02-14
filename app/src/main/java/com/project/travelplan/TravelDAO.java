package com.project.travelplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class TravelDAO {
    private static SQLiteDatabase database;
    private static SQLiteOpenHelper dbHelper;
    //private DbHelper dbHelper;

    //constructor
    public TravelDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static void open() {
        database = dbHelper.getWritableDatabase();
    }

    public static void close(){
        dbHelper.close();
    }

    public ArrayList<String> getAllName() {
        ArrayList<String> name = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM travel;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            name.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return name;
    }

    public void add(Travel a, Travel b, Travel c){
        Travel travel_name = new Travel();
        travel_name = a;
        Travel travel_lat = new Travel();
        travel_lat = b;
        Travel travel_log = new Travel();
        travel_log = c;

        ContentValues values = new ContentValues();
        values.put("name", travel_name.getName());
        values.put("latitude", travel_name.getLatitude());
        values.put("logtitude", travel_name.getLogitude());
        this.database.insert("travel",null,values);
        Log.d("บันทึกสถานที่ของคุณ", "เพิ่มเสร็จสิ้น!!");

    }
}
