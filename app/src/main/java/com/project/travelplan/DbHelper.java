package com.project.travelplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_name = "location.db";
    public static final String table_name = "location_table";
    public static final String col_1 = "id";
    public static final String col_2 = "name";
    public static final String col_3 = "latitude";
    public static final String col_4 = "logitude";

    public DbHelper(@Nullable Context context) {
        super(context, DB_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE location_table (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, latitude DOUBLE, logitude DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean AddData(String name, Double latitude, Double logitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, name);
        contentValues.put(col_3, latitude);
        contentValues.put(col_4, logitude);
        long result = db.insert(table_name, null, contentValues);
        if (result == 0){
            return false;
        }else{
            return true;
        }
    }

    public Integer DeleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer qty = db.delete(table_name, "name = ?", new String[] {name});
        return qty;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resDat = db.rawQuery("SELECT * FROM " + table_name, null);
        return resDat;
    }

    public Cursor getAllDataV2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resDat = db.rawQuery("SELECT DISTINCT name FROM " + table_name, null);
        return resDat;
    }


    /*   private static final String databaseName = "travel.sqlite";
    private static final int databaseVersion = 1;
    Context myContext;

    private static final String tableCreateSQL = "CREATE TABLE travel (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "latitude TEXT, " +
            "logitude TEXT" +
            ");";

    //constructor
    public DbHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableCreateSQL);
//        String insertData1 = "INSERT INTO travel (name, latitude, logitude) VALUES ('อุทยานแห่งชาติเขาใหญ่', '14.438946', '101.372175')";
//        db.execSQL(insertData1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }*/
}
