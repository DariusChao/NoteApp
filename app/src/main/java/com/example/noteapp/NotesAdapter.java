package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

// Adapter class for managing and displaying a list of notes in a RecyclerView
public class NotesAdapter extends ListAdapter<NoteData, NotesViewHolder> {

    public boolean trash; // Flag indicating if the adapter is in "trash" mode

    // Constructor for the NotesAdapter
    public NotesAdapter(@NonNull DiffUtil.ItemCallback<NoteData> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new NotesViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        // Bind data to the ViewHolder for each item
        NoteData note = getItem(position);
        holder.dateText.setText(note.date.substring(0, 10));
        holder.noteText.setText(note.title);
        holder.checkBox.setChecked(false);

        // Show or hide the checkbox based on the "trash" mode
        if (trash){
            holder.checkBox.setVisibility(View.VISIBLE);
        }
        else{
            holder.checkBox.setVisibility(View.GONE);
        }
    }

    // DiffUtil callback for efficient updates in the RecyclerView
    static class NoteDiff extends DiffUtil.ItemCallback<NoteData>
    {
        @Override
        public boolean areItemsTheSame(@NonNull NoteData oldItem, @NonNull NoteData newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull NoteData oldItem, @NonNull NoteData newItem) {
            // Compare the contents of old and new items to determine if they are the same
            return oldItem.note.equals(newItem.note) && oldItem.date.equals(newItem.date);
        }
    }
}