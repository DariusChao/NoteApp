# Notes App

## Overview
This Notes App is a basic Android application designed to help users create, manage, and organize notes. This project demonstrates fundamental concepts of Android app development, including data storage using Room Database, RecyclerView implementation, and ViewModel architecture.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Future Improvements](#future-improvements)

## Features
- Create and edit notes with titles and content.
- Organize notes by date of creation.
- "Trash" functionality to delete notes.
- Simple and intuitive user interface.

## Getting Started
To run this app on your local machine, you'll need to download Android Studio. Here are the steps to get started:

1. Clone this repository:
   ```bash
   git clone https://github.com/DariusChao/NoteApp.git
2. Open the project in Android Studio.
3. Build and run the app on the emulator within Android Studio or an Android device.

## Usage
1. Launch the app.
2. Create a new note by tapping the "+" button in the top right of the home page.
3. Provide a title and content for your note. (The note will not save if you do not put a title.)
4. Your note will be displayed in a list, sorted by the creation date.
5. Tap on a note to edit its contents.
6. To delete a note, enable "Trash Mode" by tapping the trash icon in the app bar. Then, select the notes to delete by checking the checkboxes.
7. To exit "Trash Mode," simply tap the trash icon again.

## Project Structure
1. AppDatabase.java: This class represents the Room Database for the app. It is responsible for creating and managing the database instance. It uses the Singleton pattern to ensure that only one database instance exists.
3. MainActivity.java: This is the main entry point of the app. It contains the code for the app's main activity, where users can view their notes and interact with the app's features.
4. NoteData.java: This class represents the data structure for a note. It includes fields for the note's unique ID, content, date of creation, title, and a "trash" flag to indicate if it's marked for deletion.
5. NoteRepository.java: The Repository class acts as an intermediary between the ViewModel and the DAO. It handles data operations like inserting, updating, and deleting notes. It also provides LiveData for observing changes in the list of notes.
6. NotesActivity.java: This activity is responsible for creating and editing individual notes. Users can input the title and content of a note within this activity.
7. NotesAdapter.java: This class is an integral part of the app's RecyclerView implementation. It acts as the adapter for the RecyclerView, providing data and creating the necessary ViewHolders to display each note item.
8. NotesDAO.java: The Data Access Object (DAO) interface defines the database operations for interacting with the Room Database. It includes methods for inserting, updating, and deleting notes, as well as querying for a list of notes.
9. NotesViewBundle.java: The ViewModel class is responsible for managing and providing access to notes data. It is tied to the app's UI components and survives configuration changes. It initializes the NoteRepository and provides LiveData for observing changes in the list of notes.
10. NotesViewHolder.java: The ViewHolder class represents individual items in the RecyclerView. It contains references to the UI elements used to display each note, such as the note's title, date, and a checkbox for managing notes in "Trash Mode."
   
## Future Improvements
The Simple Notes App is a basic starting point for a notes application. To better its functionality and user experience, I would possibly like to implement the following:

1. Rich Text Editing: Add support for rich text formatting and attachments in notes.
2. Search and Filters: Implement search functionality and filtering options for notes.
3. Themes: Enhance the app with various themes (such as dark mode) for improved usability and customizability.
4. Categories and Tags: Allow users to categorize notes using tags or categories.
5. Export and Backup: Enable users to export and backup their notes.
