package com.example.crossdle.app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.crossdle.R;
import com.example.crossdle.app.HistoryItem;

public class HistoryItemFragment extends Fragment {
    private static final String ARG_ITEM = "ARG_ITEM";

    private HistoryItem item;

    public HistoryItemFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (HistoryItem)getArguments().getSerializable(ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_item, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView idView = view.findViewById(R.id.historyItem_textView_id);
        TextView timeView = view.findViewById(R.id.historyItem_textView_time);
        TextView wordsView = view.findViewById(R.id.historyItem_textView_words);
        TextView attemptsView = view.findViewById(R.id.historyItem_textView_attempts);

        idView.setText(item.getGameId());
        timeView.setText(item.getTime().toString());
        wordsView.setText(String.valueOf(item.getWords()));
        attemptsView.setText(String.valueOf(item.getAttempts()));
    }

    public static HistoryItemFragment newInstance(HistoryItem item) {
        HistoryItemFragment fragment = new HistoryItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }
}