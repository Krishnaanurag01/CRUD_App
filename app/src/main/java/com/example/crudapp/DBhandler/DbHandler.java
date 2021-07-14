package com.example.crudapp.DBhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.crudapp.Contact.Contact;
import com.example.crudapp.PARAMS.DbParameters;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {


    public DbHandler(@Nullable Context context) {
        super(context, DbParameters.DB_Name, null, DbParameters.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+DbParameters.Table_Name+"("+
                DbParameters.key_id +" INTEGER PRIMARY KEY," + DbParameters.key_name+" TEXT, "+
                DbParameters.key_number+" TEXT"+")";
        db.execSQL(query);
        Log.d("Created", "onCreate: Sucessfully Created the table.");
//        db.close(); don't close this otherwise you'll get exception.

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContacts(Contact contact){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbParameters.key_name,contact.getName());
        values.put(DbParameters.key_number,contact.getNumber());
        sql.insert(DbParameters.Table_Name,null,values);
        Log.d("Insertion", "addContacts: Sucessfully added "+contact.getName());
        sql.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactslist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String search = "SELECT * FROM "+DbParameters.Table_Name;
        Cursor cursor = db.rawQuery(search,null);
        if (cursor.moveToFirst()){
            do{
            Contact contact = new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getString(2));
            contactslist.add(contact);
        }
        while (cursor.moveToNext());
    }
        db.close();
        return contactslist;
    }

    public int updateContacts(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbParameters.key_name,contact.getName());
        values.put(DbParameters.key_number,contact.getNumber());
        values.put(DbParameters.key_id,contact.getId());
        return db.update(DbParameters.Table_Name,values,DbParameters.key_id+"=?",new String[]{String.valueOf(contact.getId())});

    }

    public void deleteContactByID(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(DbParameters.Table_Name,DbParameters.key_id+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContactByName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbParameters.Table_Name,DbParameters.key_name+"=?",new String[]{name});
        db.close();
    }

    public int getTotalCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String search = "SELECT * FROM "+DbParameters.Table_Name;
        Cursor cursor = db.rawQuery(search,null);
        return cursor.getCount();
    }


}
