package com.example.notesapp;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    NotesSource dataSource;
    private OnItemClickListener itemClickListener;
    private ListFragment fragment;
    private int menuPosition;

    public ListAdapter(NotesSource dataSource, ListFragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    public void setNewData(List<Note> dataSource) {
        this.dataSource.setNewData(dataSource);
        notifyDataSetChanged();
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    private void registerContextMenu(View itemView) {
        if (fragment != null) {
            fragment.registerForContextMenu(itemView);
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new ListAdapter.ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(dataSource.getNote(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteTitle;
        private TextView noteDescription;
        private AppCompatImageView noteImage;
        private CheckBox noteLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteDescription = itemView.findViewById(R.id.note_description);
            noteImage = itemView.findViewById(R.id.image_view);
            noteLike = itemView.findViewById(R.id.like);

            registerContextMenu(itemView);

            noteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(view, position);
                    }
                }
            });

            noteImage.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(15, 15);
                    return true;
                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void setData(Note note) {
            noteTitle.setText(note.getTitle());
            noteDescription.setText(note.getDescription());
            noteImage.setImageResource(note.getPicture());
            noteLike.setChecked(note.isLike());
        }
    }
}