package com.example.phonecontacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

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

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity.this, this, contact_name, phone_number);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delelte_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete All?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Deleted all! ", Toast.LENGTH_SHORT).show();
                DbManager myDB = new DbManager(MainActivity.this);
                myDB.deleteAll();

                // Refresh
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    /*@Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }*/

}