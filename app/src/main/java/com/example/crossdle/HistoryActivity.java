package com.example.crossdle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.time.LocalTime;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        updateRecycler(R.id.history_recyclerView, new HistoryItem[] {
            new HistoryItem(LocalTime.now(), 5, 10),
            new HistoryItem(LocalTime.now(), 2, 4),
            new HistoryItem(LocalTime.now(), 100, 200),
        });
    }

    private void updateRecycler(int recyclerViewId, HistoryItem[] data) {
        RecyclerView recycler = findViewById(recyclerViewId);
        HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}