package com.example.phonecontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PhoneContact.db";

    private static final String TABLE_NAME = "table_phone_contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_NUMBER = "phone_number";

    public DbManager(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("DbManager: onCreate");

        String query =  "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT);";
        db.execSQL( query );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("DbManager: onUpgrade");

        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public String addRecord( String p1, String p2) {

        System.out.println("DbManager: addRecord");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, p1);
        cv.put(COLUMN_NUMBER, p2);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    public Cursor readAllData() {

        System.out.println("DbManager: readAllData");

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /*{
        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = db.rawQuery(SELECT_ALL_CONTACTS_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = getContact(cursor);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
        return contactList;
    }*/
}
