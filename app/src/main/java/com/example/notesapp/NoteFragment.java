package com.example.notesapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteFragment extends Fragment {

    //static final String ARG_INDEX = "index";
    static final String SELECTED_NOTE = "note";
    private Note note;

    public NoteFragment() {
        // Required empty public constructor
    }

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_INDEX, index);
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            //int index = arguments.getInt(ARG_INDEX);
            note = arguments.getParcelable(SELECTED_NOTE);

            TextView tvTitle = view.findViewById(R.id.tvTitle);
            //tvTitle.setText(Note.getNotes()[index].getTitle());
            tvTitle.setText(note.getTitle());

            TextView tvDescription = view.findViewById(R.id.tvDescription);
            //tvDescription.setText(Note.getNotes()[index].getDescription());
            tvDescription.setText(note.getDescription());
        }

        TextView tvForLink = view.findViewById(R.id.tvLink);
        tvForLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // requireParentFragment().getChildFragmentManager()
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.note_child_link_container, new NoteChildFragment())
                        .addToBackStack("")
                        .commit();
            }
        });
    }
}