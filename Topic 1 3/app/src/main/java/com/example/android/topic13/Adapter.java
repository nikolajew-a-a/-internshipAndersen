package com.example.android.topic13;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> values;
    List<Drawable> figures;

    Adapter(List<String> values, List<Drawable> figures){
        this.values = values;
        this.figures = figures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(values.get(position));
        holder.position = position;
        holder.imageView.setImageDrawable(figures.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        DialogFragment dialog;
        TextView textView;
        ImageView imageView;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.myTextView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog();
                    Bundle bundle = new Bundle();
                    String itemName = "element № " + position;
                    bundle.putString("name", itemName);
                    dialog.setArguments(bundle);
                    dialog.show(((Activity) v.getContext()).getFragmentManager(),"dialog");
                }
            });
        }
    }
}