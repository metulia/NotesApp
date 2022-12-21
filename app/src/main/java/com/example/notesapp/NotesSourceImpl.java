package com.example.notesapp;

import android.content.res.Resources;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class NotesSourceImpl implements NotesSource {

    private List<Note> dataSource;
    private Resources resources;

    public NotesSourceImpl(Resources resources) {
        this.resources = resources;
        dataSource = new ArrayList<>(10);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotesSourceImpl init() {
        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        for (int i = 0; i < 10; i++) {
            dataSource.add(new Note(titles[i], descriptions[i]));
        }
        return this;
    }

    @Override
    public Note getNoteData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}