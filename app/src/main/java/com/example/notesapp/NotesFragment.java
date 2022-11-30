package com.example.notesapp;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesFragment extends Fragment {

    static final String SELECTED_INDEX = "index";
    private int selectedIndex = 0;
    private Note note;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SELECTED_INDEX, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX, 0);
        }

        initNotes(view.findViewById(R.id.notes_container));

        if (isLandscape()) {
            showLandNoteDetails(selectedIndex);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotes(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < Note.getNotes().length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(Note.getNotes()[i].getTitle());
            tv.setTextSize(35);
            tv.setTypeface(Typeface.SERIF);
            layoutView.addView(tv);

            final int index = i;
            tv.setOnClickListener(v -> {
                showNoteDetails(index);
            });
        }
    }

    private void showNoteDetails(int index) {
        selectedIndex = index;
        if (isLandscape()) {
            showLandNoteDetails(index);
        } else showPortNoteDetails(index);
    }

    private void showPortNoteDetails(int index) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, NoteFragment.newInstance(index));
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showLandNoteDetails(int index) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment2_container, NoteFragment.newInstance(index));
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}