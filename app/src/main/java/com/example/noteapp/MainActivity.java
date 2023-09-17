package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    NotesAdapter noteAdap;
    private NotesViewBundle noteModel;
    public static final int newWordActivityRequestCode = 1;
    public static final int updateActivityRequest = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and its adapter
        recycler = findViewById(R.id.recyclerView);
        noteAdap = new NotesAdapter(new NotesAdapter.NoteDiff());
        recycler.setAdapter(noteAdap);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ViewModel for managing notes data
        noteModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NotesViewBundle.class);

        // Observe changes in the notes data and update the adapter
        noteModel.notes.observe(this, note -> {
            noteAdap.submitList(note);
        });

        // Retrieve data from the intent and update the ViewModel
        Intent intent = getIntent();
        Log.d("MainActivity", "hello");
        NoteData note = new NoteData(intent.getStringExtra("note"), intent.getLongExtra("id", 1), intent.getStringExtra("title"));
        noteModel.update(note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.addButton:
                // Open the NotesActivity for adding a new note
                Intent intent = new Intent(this, NotesActivity.class);
                startActivityForResult(intent, MainActivity.newWordActivityRequestCode);
                return true;
            case R.id.deleteButton:
                if (noteAdap.trash) {
                    // If in "trash" mode, delete selected notes
                    noteAdap.trash = false;
                    List<NoteData> fNote = noteAdap.getCurrentList();
                    for (int i = fNote.size() - 1; i > -1; i--) {
                        if (fNote.get(i).trash == 1) {
                            NoteData notes = fNote.get(i);
                            noteModel.delete(notes);
                        }
                    }
                } else {
                    // Toggle "trash" mode
                    noteAdap.trash = true;
                }
                noteAdap.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(menu);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == newWordActivityRequestCode && resultCode == RESULT_OK) {
            // Retrieve new note data from NotesActivity and insert it into the ViewModel
            NoteData note = new NoteData(data.getStringExtra("note"), -1, data.getStringExtra("title"));
            noteModel.insert(note);
        }
    }
}