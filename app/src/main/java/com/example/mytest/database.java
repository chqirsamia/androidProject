package com.example.mytest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String DBNAME = "covoiturage.db";
    public database(Context context) {
        super(context,"covoiturage.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase covoiturage) {
        covoiturage.execSQL("create Table users(id INTEGER PRIMARY KEY   AUTOINCREMENT, password TEXT, name TEXT" +
                ", lastname TEXT, email TEXT, cin TEXT, sexe TEXT,role text,datenaissance text" +
                ",pdp blob,cinphotorecto blob,cinphotoverso blob,tel int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase covoiturage, int i, int i1) {
        covoiturage.execSQL("drop Table if exists users");
    }
    public Boolean insertData1(String email, String password, String name, String lastname
            , String role, String datenaissance){
        SQLiteDatabase covoiturage = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("lastname", lastname);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);

        contentValues.put("datenaissance", datenaissance);
        contentValues.put("role", role);

        long result = covoiturage.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertData(String email, String password, String name, String lastname
            , String role, String datenaissance,String phone,String cin,String sexe){
        SQLiteDatabase covoiturage = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("lastname", lastname);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("tel", phone);
        contentValues.put("cin", cin);
        contentValues.put("sexe",sexe );
        contentValues.put("datenaissance", datenaissance);
        contentValues.put("role", role);

        long result = covoiturage.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertsuiteData(String phone,String cin,String sexe){
        SQLiteDatabase covoiturage = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("tel", phone);
        contentValues.put("cin", cin);
        contentValues.put("sexe",sexe );
        long result = covoiturage.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase covoiturage = this.getWritableDatabase();
        Cursor cursor = covoiturage.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkpassword(String email, String password){
        SQLiteDatabase covoiturage = this.getWritableDatabase();
        Cursor cursor = covoiturage.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

