package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// Define an Entity class representing a NoteData object for Room database
@Entity
public class NoteData {

    @PrimaryKey
    long id; // Primary key for the note

    @NonNull
    @ColumnInfo(name = "note")
    public String note; // Content of the note

    @ColumnInfo(name = "date")
    public String date; // Date and time when the note was created

    @ColumnInfo(name = "title")
    public String title; // Title of the note

    public int trash; // Flag indicating if the note is in the "trash"

    // Constructor for creating a NoteData object
    public NoteData(@NonNull String note, long id, String title) {
        this.note = note;
        this.title = title;

        // Generate a random ID if not provided
        if (id != -1) {
            this.id = id;
        } else {
            Random r = new Random();
            long num = r.nextLong();
            while (num == -1) {
                num = r.nextLong();
            }
            this.id = num;
        }

        // Generate the current date and time as a string
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        date = dateFormat.format(new Date());
    }
}