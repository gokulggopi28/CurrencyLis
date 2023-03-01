package com.example.currencylis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    DBHelper dbHelper;
    SQLiteDatabase database;
    Context context;


    public DBManager(Context c){

        context = c;

    }

    public  DBManager Open(){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return  this;
    }

    public void  Close(){

        dbHelper.close();
    }

    public  void Insert(String country,String currency){

        ContentValues values = new ContentValues();
        values.put(dbHelper._NAME,country);
        values.put(dbHelper._CURRENCY,currency);

        database.insert(dbHelper.TABLE_NAME,null, values);

    }

    public  Cursor fetch()
    {
        String [] columns = new String[] {

                DBHelper._ID,
                DBHelper._NAME,
                DBHelper._CURRENCY

        };

        Cursor query =database.query(DBHelper.TABLE_NAME, columns, null,null,null,null,null);

        if(query !=null){
            query.moveToFirst();
        }

        return query;




    }

}
