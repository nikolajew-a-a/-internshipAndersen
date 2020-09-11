package com.example.android.topic13;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(generateFakeValues(), generateFakeFigures()));
    }

    private List<String> generateFakeValues() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            values.add(i + "element");
        }
        return values;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private List<Drawable> generateFakeFigures() {
        List<Drawable> figures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i%2 == 0) {
                figures.add((Drawable) getDrawable(R.drawable.circle));
            } else if (i%3 == 0) {
                figures.add((Drawable) getDrawable(R.drawable.triangle));
            } else {
                figures.add((Drawable) getDrawable(R.drawable.square));
            }

        }
        return figures;
    }
}
