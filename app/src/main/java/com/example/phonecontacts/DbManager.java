package com.example.phonecontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbManager extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "PhoneContact.db";

    private static final String TABLE_NAME = "table_phone_contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_NUMBER = "phone_number";

    DbManager(Context context) {

        super(context, DATABASE_NAME, null, 1);
        this.context = context;
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

    void updateData(String row_id, String p1, String p2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, p1 );
        cv.put(COLUMN_NUMBER, p2 );

        System.out.println("^^^BEFORE UPDATE ^^^ " + row_id + " " + p1 + " " + p2);
        long result = db.update( TABLE_NAME, cv, "_id=?", new String[]{row_id} );

        if(result == -1) {
            Toast.makeText(context, "Failed to update... ", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("^^^REsult^^^ "+ result);
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[] {row_id});
        if(result == -1) {
            Toast.makeText(context, "Fail to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
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
