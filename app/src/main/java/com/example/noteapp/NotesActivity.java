package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

// Activity for creating and editing notes
public class NotesActivity extends AppCompatActivity {
    EditText editTxt;
    EditText title;
    int index;
    public static final String reply = "com.example.noteapp.notelistsql.REPLY";
    long id; // Unique ID for the note

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Intent intent = getIntent();

        // Retrieve the note ID from the intent
        id = intent.getLongExtra("id", -1);

        // Initialize UI elements
        editTxt = findViewById(R.id.editTxt);
        title = findViewById(R.id.title);

        // Populate UI elements with existing note data if in edit mode
        if (index != -1) {
            editTxt.setText(intent.getStringExtra("note"));
            title.setText(intent.getStringExtra("title"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notesappbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.saveButton:
                String text = editTxt.getText().toString();
                Intent intent = new Intent(this, MainActivity.class);
                String titleStr = title.getText().toString();
                if (!titleStr.equals("")) {
                    intent.putExtra("note", text);
                    intent.putExtra("id", id);
                    intent.putExtra("title", titleStr);

                    // Set the result to indicate successful note creation or update
                    if (id == -1) {
                        setResult(RESULT_OK, intent);
                    } else {
                        startActivity(intent);
                    }
                } else {
                    // Set the result to indicate that the note creation or update was canceled
                    setResult(RESULT_CANCELED, intent);
                }

                // Close the NotesActivity
                finish();
                return true;
        }
        return super.onOptionsItemSelected(menu);
    }
}