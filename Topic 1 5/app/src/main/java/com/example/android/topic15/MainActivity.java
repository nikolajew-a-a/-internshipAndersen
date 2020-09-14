package com.example.android.topic15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Портретная ориентация", Toast.LENGTH_SHORT).show();
            textView1.setText("Text top");
            textView2.setText("Text bottom");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Альбомная ориентация", Toast.LENGTH_SHORT).show();
            textView1.setText("Text left");
            textView2.setText("Text right");
        }
    }
}
