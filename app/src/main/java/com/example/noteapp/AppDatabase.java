package com.example.noteapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Define a Room database with NoteData entity and version 1
@Database(entities = {NoteData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Declare an abstract method to access the NotesDAO
    public abstract NotesDAO noteDao();

    private static AppDatabase instance;
    private static final int numberThreads = 4;

    // Create an ExecutorService to perform database operations on multiple threads
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(numberThreads);

    // Create or retrieve a database instance using Room
    static AppDatabase getDatabase(final Context context){
        if (instance == null)
        {
            synchronized (AppDatabase.class){
                if (instance == null)
                {
                    // Build the Room database using the application context
                    // and the AppDatabase class with the name "NoteDatabase"
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "NoteDatabase").build();
                }
            }
        }
        return instance;
    }
}