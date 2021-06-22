package com.verinite.smartcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "smart_card.db";
    public static String DBPath;

    Context context;

    public DBHelper( Context context) {
        super(context,DBName,null,1);
        this.context = context;
        this.DBPath = this.context.getDatabasePath(DBName).getAbsolutePath();
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table IF NOT EXISTS login(username TEXT primary key, password TEXT)");
        myDB.execSQL("create table IF NOT EXISTS cards_details(card_no TEXT primary key, format TEXT, card_holder_name TEXT, " +
                "card_name TEXT, pin TEXT, cvv_no TEXT, service_code TEXT, expire_date Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop table if exists login  ");
        myDB.execSQL("drop table if exists cards_details");
    }

    public Boolean insertUserCredential(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result =myDB.insert("login", null, contentValues);
        return result == -1 ? false : true;
    }

    public Boolean checkUsernameExists(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from login where username = ?", new String[] {username});
        return cursor.getCount() > 0 ? true : false;
    }

    public Boolean checkUsernameAndPassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from login where username = ? and password = ?", new String[] {username, password});
        return cursor.getCount() > 0 ? true : false;
    }

    public Boolean insertCard(String card_no, String format, String card_holder_name,
                              String card_name, String pin, String cvv_no, String service_code, String expire_date){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("card_no", card_no);
        contentValues.put("format", format);
        contentValues.put("card_holder_name", card_holder_name);
        contentValues.put("card_name", card_name);
        contentValues.put("pin", pin);
        contentValues.put("cvv_no", cvv_no);
        contentValues.put("service_code", service_code);
        contentValues.put("expire_date", expire_date);
        long result =myDB.insert("cards_details", null, contentValues);
        return result == -1 ? false : true;

    }

    public List<Map<String,String>> fetchCardName(){
        List<Map<String,String>> list = new ArrayList<>();
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select card_no, card_name from cards_details", null);
        if (cursor.moveToFirst()){
            do {
                Map<String,String> data = new HashMap<>();
                String card_no = cursor.getString(0);
                String card_name = cursor.getString(1);
                Log.d("Data is",card_no + " " + card_name);
                data.put(card_no,card_name);
                list.add(data);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public Boolean checkUsernameExists(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from login", null);
        return cursor.getCount() > 0 ? true : false;
    }
}
