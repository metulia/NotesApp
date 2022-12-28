package com.example.notesapp;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class NotesSourceImpl implements NotesSource {

    private List<Note> dataSource;
    //private Resources resources;

    public NotesSourceImpl(/*Resources resources*/) {
        //this.resources = resources;
        dataSource = new ArrayList<>();
    }

    /*
    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }
     */

    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotesSourceImpl init() {
        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        int[] pictures = getImageArray();
        for (int i = 0; i < 10; i++) {
            dataSource.add(new Note(titles[i], descriptions[i], pictures[i], false));
        }
        return this;
    }
     */

    @Override
    public Note getNote(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteNote(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        dataSource.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        dataSource.add(note);
    }

    @Override
    public void clear() {
        dataSource.clear();
    }

    @Override
    public void setNewData(List<Note> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Note> getNoteData() {
        return dataSource;
    }
}