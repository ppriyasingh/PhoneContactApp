package com.example.phonecontacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {

    EditText name_input, number_input;
    String contactVal, phoneVal, id;
    Button update_button, delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        name_input = findViewById(R.id.editContactName2);
        number_input = findViewById(R.id.editContactNumber2);
        update_button = findViewById(R.id.updateContact);
        delete_button = findViewById(R.id.deleteContact);

        getAndSetIntentData();

        // Set Actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(contactVal);
        }

        update_button.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DbManager myDB = new DbManager(UpdateContact.this);

                contactVal = name_input.getText().toString().trim();
                phoneVal = number_input.getText().toString().trim();
                myDB.updateData(id, contactVal, phoneVal);
            }
        } );

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") &&  getIntent().hasExtra("contactData") && getIntent().hasExtra("phoneData")) {

            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            contactVal = getIntent().getStringExtra("contactData");
            phoneVal = getIntent().getStringExtra("phoneData");

            //Setting Intent data
            name_input.setText(contactVal);
            number_input.setText(phoneVal);

        } else {
            Toast.makeText(this, "No data..", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ contactVal+"?");
        builder.setMessage("Are you sure you want to delete "+contactVal+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbManager myDB = new DbManager(UpdateContact.this);
                myDB.deleteOneRow(id);
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
}