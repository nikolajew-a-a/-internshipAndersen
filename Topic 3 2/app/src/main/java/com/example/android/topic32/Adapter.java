package com.example.android.topic32;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
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

    private static final String SET_TRACK_ACTION = "com.example.android.topic32.SetTrack";
    private static final String SONG_NAME = "name";
    private static final String SONG_BAND = "band";
    private static final String SONG_STYLE = "style";
    private static final String SONG_URI = "uri";

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
        private Song song;

        public void bind(@NotNull Song song) {
            this.song = song;
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
                    Context context = v.getContext();
                    Intent intent = new Intent();
                    intent.setAction(SET_TRACK_ACTION);
                    intent.putExtra(SONG_NAME, song.getSongName());
                    intent.putExtra(SONG_BAND, song.getSongBand());
                    intent.putExtra(SONG_STYLE, song.getSongStyle());
                    intent.putExtra(SONG_URI, song.getSongUri());
                    sendBroadcastExplicit(context, intent);
                    Log.d("mLog", "Отправлен 3.2");
                }
            });
        }

        private void sendBroadcastExplicit(@NotNull Context context, @NotNull Intent intent) {
            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> matches = pm.queryBroadcastReceivers(intent, 0);
            for (ResolveInfo resolveInfo : matches) {
                Intent explicit = new Intent(intent);
                ComponentName cn =
                        new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName,
                                resolveInfo.activityInfo.name);
                explicit.setComponent(cn);
                context.sendBroadcast(explicit);
            }
        }
    }
}
