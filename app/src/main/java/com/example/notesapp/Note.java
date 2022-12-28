package com.example.notesapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.Date;

public class Note /*implements Parcelable*/ {

    //private static final Random random = new Random();
    //private static Note[] notes;

    private String title;
    private String description;
    private int picture;
    private boolean like;
    private Date dateOfCreation;

    /*public static Note[] getNotes() {
        return notes;
    }*/

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

    public Date getDateOfCreation() {
        return dateOfCreation;
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

    public Note(String title, String description, int picture, boolean like, Date dateOfCreation) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
        this.dateOfCreation = dateOfCreation;
    }

    /*
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

     */
}