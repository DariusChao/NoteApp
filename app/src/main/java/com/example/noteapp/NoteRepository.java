package com.example.noteapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.List;

// Repository class to manage data operations between the ViewBundle and the DAO
public class NoteRepository {

    public NotesDAO noteDao; // Data Access Object for notes
    public LiveData<List<NoteData>> allNotes; // LiveData for all notes

    // Constructor to initialize the repository
    NoteRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        noteDao = db.noteDao(); // Initialize the DAO
        allNotes = noteDao.getAll(); // Get LiveData for all notes
    }

    // Insert a new note asynchronously
    void insert(NoteData note){
        AppDatabase.databaseWriteExecutor.execute(()->{
            noteDao.insert(note);
        });
    }

    // Update an existing note asynchronously
    void update(NoteData note){
        AppDatabase.databaseWriteExecutor.execute(()->{
            noteDao.update(note);
        });
    }

    // Delete a note asynchronously
    void delete(NoteData note){
        AppDatabase.databaseWriteExecutor.execute(()->{
            noteDao.delete(note);
        });
    }
}