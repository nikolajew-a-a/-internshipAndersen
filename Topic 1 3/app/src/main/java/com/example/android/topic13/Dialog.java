package com.example.android.topic13;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.DialogFragment;
import android.widget.TextView;


public class Dialog extends DialogFragment implements View.OnClickListener {
    public TextView textView;
    private final String ITEM_NAME = "name";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog, null);
        v.findViewById(R.id.btnOK).setOnClickListener(this);

        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString(ITEM_NAME);
            textView = v.findViewById(R.id.textView1);
            textView.setText(name);
        }

        return v;
    }


    public void onClick(View v) {
        dismiss();
    }
}
