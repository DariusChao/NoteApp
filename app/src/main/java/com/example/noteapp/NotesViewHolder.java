package com.example.noteapp;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// ViewHolder class for displaying individual notes in a RecyclerView
public class NotesViewHolder extends RecyclerView.ViewHolder {

    public NotesAdapter adapter; // Reference to the adapter
    public TextView noteText; // TextView for displaying note content
    public TextView dateText; // TextView for displaying note date
    public CheckBox checkBox; // CheckBox for selecting notes

    // Constructor to initialize the ViewHolder
    public NotesViewHolder(@NonNull View itemView, final NotesAdapter adapter) {
        super(itemView);
        this.adapter = adapter;

        // Initialize UI elements
        noteText = itemView.findViewById(R.id.noteText);
        dateText = itemView.findViewById(R.id.dateText);
        checkBox = itemView.findViewById(R.id.checkBox);

        // Set click listener for opening the NotesActivity
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NotesActivity.class);
                NoteData note = adapter.getCurrentList().get(getAdapterPosition());
                intent.putExtra("note", note.note);
                intent.putExtra("id", note.id);
                intent.putExtra("title", note.title);
                view.getContext().startActivity(intent);
            }
        });

        // Set click listener for updating the "trash" status of a note
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    adapter.getCurrentList().get(getAdapterPosition()).trash = 1;
                } else {
                    adapter.getCurrentList().get(getAdapterPosition()).trash = 0;
                }
            }
        });
    }
}