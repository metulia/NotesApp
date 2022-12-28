package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListFragment extends Fragment {

    private NotesSource data;
    private ListAdapter adapter;
    private RecyclerView recyclerView;
    private static int DURATION = 2000;

    private SharedPreferences sharedPreferences;
    public static final String DATA_KEY = "DATA_KEY";

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        switch (item.getItemId()) {
            case R.id.action_add:
                data.addNote(new Note("Новая заметка #" + data.size(), "Описание заметки #" + data.size(),
                        R.drawable.nature11, false));
                adapter.notifyItemInserted(data.size() - 1);
                recyclerView.scrollToPosition(data.size() - 1);
                recyclerView.smoothScrollToPosition(data.size() - 1);

                String jsonNoteDataAfterAdd = new GsonBuilder().create().toJson(data.getNoteData());
                sharedPreferences.edit().putString(DATA_KEY, jsonNoteDataAfterAdd).apply();

                return true;

            case R.id.action_clear:
                data.clear();
                adapter.notifyDataSetChanged();

                String jsonNoteDataAfterClear = new GsonBuilder().create().toJson(data.getNoteData());
                sharedPreferences.edit().putString(DATA_KEY, jsonNoteDataAfterClear).apply();

                return true;
        }
        return super.onOptionsItemSelected(item);
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

        initView(view);
        setHasOptionsMenu(true);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycler_view_lines);
        //data = new NotesSourceImpl(getResources()).init();
        data = new NotesSourceImpl();
        initRecyclerView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initRecyclerView() {

        adapter = new ListAdapter(data, this);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemItemDecoration = new
                DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemItemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemItemDecoration);

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(DURATION);
        defaultItemAnimator.setRemoveDuration(DURATION);
        recyclerView.setItemAnimator(defaultItemAnimator);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedData = sharedPreferences.getString(DATA_KEY, null);
        if (savedData == null) {
            Toast.makeText(getActivity(), "Empty data", Toast.LENGTH_LONG).show();
        } else {
            try {
                Type type = new TypeToken<List<Note>>() {
                }.getType();
                adapter.setNewData(new GsonBuilder().create().fromJson(savedData, type));
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        }

        adapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), String.format("Позиция - %d", position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v,
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {

        requireActivity().getMenuInflater().inflate(R.menu.note_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //getActivity().getSharedPreferences();
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        int position = adapter.getMenuPosition();

        switch (item.getItemId()) {

            case R.id.action_update:

                Note note = data.getNote(position);
                data.updateNote(position, new Note("Элемент " + position, note.getDescription(),
                        note.getPicture(), note.isLike()));
                adapter.notifyItemChanged(position);

                String jsonNoteDataAfterUpdate = new GsonBuilder().create().toJson(data.getNoteData());
                sharedPreferences.edit().putString(DATA_KEY, jsonNoteDataAfterUpdate).apply();

                return true;

            case R.id.action_delete:
                data.deleteNote(position);
                adapter.notifyItemRemoved(position);

                String jsonNoteDataAfterDelete = new GsonBuilder().create().toJson(data.getNoteData());
                sharedPreferences.edit().putString(DATA_KEY, jsonNoteDataAfterDelete).apply();

                return true;
        }
        return super.onContextItemSelected(item);
    }
}