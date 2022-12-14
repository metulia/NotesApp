package com.example.notesapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Note implements Parcelable {

    private static final Random random = new Random();
    private static Note[] notes;
    private String title;
    private String description;
    private int picture;
    private boolean like;
    private LocalDateTime dateOfCreation;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public static Note[] getNotes() {
        return notes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public Note(String title, String description, LocalDateTime dateOfCreation) {
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Note(String title, String description, int picture, boolean like) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
    }

    // инициализатор массива заметок
    static {
        notes = new Note[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.createNote(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("DefaultLocale")
    public static Note createNote(int index) {
        String title = String.format("Заметка %d", index);
        String description = String.format("Описание заметки %d", index);
        LocalDateTime dateOfCreation = LocalDateTime.now().plusDays(-random.nextInt(5));
        return new Note(title, description, dateOfCreation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getDescription());
    }

    protected Note(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel parcel) {
            return new Note(parcel);
        }

        @Override
        public Note[] newArray(int i) {
            return new Note[i];
        }
    };
}