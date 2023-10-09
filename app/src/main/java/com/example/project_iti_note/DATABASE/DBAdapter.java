package com.example.project_iti_note.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
   public DatabaseHelper databaseHelper  ;
    public DBAdapter(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public long insertentery(MODEL entery){
        SQLiteDatabase dp = databaseHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(DatabaseHelper.COL_NOTE_DETAILS , entery.getNote_details());
        contentValues.put(DatabaseHelper.COL_NOTE_NAME,entery.getNote_name());
        long id = dp.insert(DatabaseHelper.DATABASE_TABLE_NAME,null , contentValues);

        return id ;
    }

    public MODEL getentry(){
        MODEL entry = null ;
        Cursor c ;
        SQLiteDatabase dp = databaseHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_NOTE_NAME , DatabaseHelper.COL_NOTE_DETAILS};
        c = dp.query(DatabaseHelper.DATABASE_TABLE_NAME , columns ,null ,null , null ,null ,null);
        while (c.moveToNext()){
            entry = new MODEL(c.getString(0),c.getString(1));
        }
        return entry ;
    }
    public String getdetailinentry(String name){
        String entry = null ;
        Cursor c ;
        SQLiteDatabase dp = databaseHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_NOTE_DETAILS};
        String whereClause = DatabaseHelper.COL_NOTE_NAME +" = ? ";
        c = dp.query(DatabaseHelper.DATABASE_TABLE_NAME , columns ,whereClause ,new String[]{name} , null ,null ,null);
        while (c.moveToNext()){
            entry = c.getString(0);
        }
        return entry ;
    }
    public MODEL[] getallentries(){
        MODEL [] model = null ;
        int i=0;
        Cursor c;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = { DatabaseHelper.COL_NOTE_NAME , DatabaseHelper.COL_NOTE_DETAILS};
        c = db.query(DatabaseHelper.DATABASE_TABLE_NAME , columns , null , null , null , null , null);
        model = new MODEL[c.getCount()] ;
        MODEL m = new MODEL();
        while (c.moveToNext()){
            m.setNote_name(c.getString(0));
            m.setNote_details(c.getString(1));
            model[i++] = m ;
        }
        return model ;
    }
    public List<String> getallNotsname(){
        List<String> names = null ;
        Cursor c;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_NOTE_NAME };
        c = db.query(DatabaseHelper.DATABASE_TABLE_NAME , columns , null , null , null , null , null);
        names = new ArrayList<>();

        while (c.moveToNext()){
            names.add(c.getString(0)) ;
        }
        return names ;
    }

   public void delete(String name) {
       SQLiteDatabase db = databaseHelper.getWritableDatabase();
       db.delete(DatabaseHelper.DATABASE_TABLE_NAME, "name=?", new String[]{name});
   }

   public void edit(String originalNOTEName, MODEL entery) {
           SQLiteDatabase db = databaseHelper.getWritableDatabase();
           ContentValues contentValues = new ContentValues();
           contentValues.put(DatabaseHelper.COL_NOTE_DETAILS, entery.getNote_details());
           contentValues.put(DatabaseHelper.COL_NOTE_NAME, entery.getNote_name());
           String whereClause = DatabaseHelper.COL_NOTE_NAME + " = ? ";
           db.update(DatabaseHelper.DATABASE_TABLE_NAME, contentValues, whereClause, new String[]{originalNOTEName});
   }




    class DatabaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "NOTE_LIST";
        private static final String DATABASE_TABLE_NAME = "NOTE";
        private static final String COL_ID = "id";
        private static final String COL_NOTE_NAME = "name";
        private static final String COL_NOTE_DETAILS = "details";
        private static final String CREATE_TABLE_QUERY= "CREATE TABLE "+DATABASE_TABLE_NAME+" ( "+COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NOTE_NAME + " TEXT NOT NULL, "+ COL_NOTE_DETAILS + " TEXT NOT NULL );";

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }

}
