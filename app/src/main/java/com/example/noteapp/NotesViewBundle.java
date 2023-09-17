package com.example.noteapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// ViewModel class for managing and providing access to notes data
public class NotesViewBundle extends AndroidViewModel {
    public NoteRepository nr; // Repository for data operations
    public final LiveData<List<NoteData>> notes; // LiveData for all notes

    // Constructor to initialize the ViewModel
    public NotesViewBundle(Application application) {
        super(application);
        nr = new NoteRepository(application); // Initialize the repository
        notes = nr.allNotes; // Get LiveData for all notes from the repository
    }

    // Insert a new note
    void insert(NoteData note){
        nr.insert(note);
    }

    // Update an existing note
    void update(NoteData note) {
        nr.update(note);
    }

    // Delete a note
    void delete(NoteData note){
        nr.delete(note);
    }
}