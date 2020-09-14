package com.example.android.topic13;

import android.app.Activity;
import android.app.DialogFragment;
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
    List<Figure> listOfFigures;
    private final String ITEM_NAME = "name";

    public Adapter(List<Figure> listOfFigures) {
        this.listOfFigures = listOfFigures;
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
        holder.bind(listOfFigures, position);
    }

    @Override
    public int getItemCount() {
        return listOfFigures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private DialogFragment dialog;
        private TextView textView;
        private ImageView imageView;
        private int position;

        public void bind(List<Figure> listOfFigures, int position) {
            this.textView.setText(listOfFigures.get(position).value);
            this.position = position;
            this.imageView.setImageDrawable(listOfFigures.get(position).figure);
        }

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
                    bundle.putString(ITEM_NAME, itemName);
                    dialog.setArguments(bundle);
                    dialog.show(((Activity) v.getContext()).getFragmentManager(),"dialog");
                }
            });
        }
    }
}