package com.example.noteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Define a Data Access Object (DAO) interface for NoteData entities
@Dao
public interface NotesDAO {

    // Query to retrieve all notes ordered by date in descending order
    @Query("select * from NoteData order by date desc")
    LiveData<List<NoteData>> getAll();

    // Insert one or more NoteData objects into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteData... note);

    // Delete one or more NoteData objects from the database
    @Delete
    void delete(NoteData... note);

    // Update a NoteData object in the database
    @Update
    void update(NoteData note);
}