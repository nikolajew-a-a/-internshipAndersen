package com.example.android.topic32;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private List<Song> listOfSongs;

    public Adapter(@NotNull List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = listOfSongs.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return listOfSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameSong;
        private TextView bandSong;
        private TextView styleSong;


        public void bind(@NotNull Song song) {
            this.nameSong.setText(song.getSongName());
            this.bandSong.setText(song.getSongBand());
            this.styleSong.setText(song.getSongStyle());
        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nameSong = itemView.findViewById(R.id.song_name);
            bandSong = itemView.findViewById(R.id.song_band);
            styleSong = itemView.findViewById(R.id.song_style);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Выбрана песня: \n" + nameSong.getText(), LENGTH_SHORT).show();
                }
            });
        }
    }
}
