package com.example.phonecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewContact extends AppCompatActivity {

    EditText editContactName, editContactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        System.out.println("AddNewContact: onCreate");

        editContactName = (EditText) findViewById(R.id.editContactName);
        editContactNumber = (EditText) findViewById(R.id.editContactNumber);
    }

    public void addNewContact(View v) {
        System.out.println("AddNewContact: addNewContact");

        DbManager db = new DbManager(this);
        String res = db.addRecord(editContactName.getText().toString().trim(), editContactNumber.getText().toString().trim());
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
//        finish();
    }
}