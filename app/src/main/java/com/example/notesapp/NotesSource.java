package com.example.notesapp;

import java.util.List;

public interface NotesSource {

    Note getNote(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Note note);

    void addNote(Note note);

    void clear();

    void setNewData (List<Note> dataSource);

    List<Note> getNoteData ();




}
