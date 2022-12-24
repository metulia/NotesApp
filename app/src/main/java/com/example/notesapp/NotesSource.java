package com.example.notesapp;

public interface NotesSource {

    Note getNoteData(int position);

    int size();
}
