package com.example.notesapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);

        NotesSource data = new NotesSourceImpl(getResources()).init();

        initRecyclerView(recyclerView, data);

        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, NotesSource data) {

        ListAdapter listAdapter = new ListAdapter(data);
        recyclerView.setAdapter(listAdapter);

        DividerItemDecoration itemItemDecoration = new
                DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemItemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemItemDecoration);
    }
}