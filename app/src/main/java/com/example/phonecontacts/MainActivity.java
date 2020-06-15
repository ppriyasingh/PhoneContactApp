package com.example.phonecontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
//    FloatingActionButton add_button;

    DbManager myDB;
    ArrayList<String> contact_name, phone_number;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("MainActivity: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DbManager(this);
        contact_name = new ArrayList<>();
        phone_number = new ArrayList<>();

        storeDataInArrays();

        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvAnimals);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, contact_name, phone_number);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void storeDataInArrays() {

        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data found...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                contact_name.add(cursor.getString(1));
                phone_number.add(cursor.getString(2));
            }
        }
    }

    public void onClick(View v) {
        System.out.println("MainActivity: onClick");

//        add_button = (FloatingActionButton) v;
        startActivity(new Intent(MainActivity.this, AddNewContact.class));
    }

    /*@Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }*/

}