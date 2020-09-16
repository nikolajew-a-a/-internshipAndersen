package com.example.android.topic15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String COUNT = "count";
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        TextView textView3 = findViewById(R.id.text3);
        TextView textView4 = findViewById(R.id.text4);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT);
            count++;
        } else {
            count = 0;
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Портретная ориентация. \n Активи пересоздано " + count + " раз(а).",
                    Toast.LENGTH_SHORT).show();
            textView1.setText("Text top");
            textView2.setText("Text bottom");
            textView3.setText("Only for medium and medium screens");
            textView4.setText("Only for big screen");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Альбомная ориентация\n Активи пересоздано " + count + " раз(а).",
                    Toast.LENGTH_SHORT).show();
            textView1.setText("Text left");
            textView2.setText("Text right");
            textView3.setText("Only for medium and medium screens");
            textView4.setText("Only for big screen");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT, count);
    }
}
