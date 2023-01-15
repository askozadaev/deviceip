package ru.akozadaev.deviceiphistory.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import ru.akozadaev.deviceiphistory.ApiService;
import ru.akozadaev.deviceiphistory.R;

import java.util.List;


public class HistoryFragment extends Fragment {
    Button getHistoryButton;
    Button clearHistoryButton;
    ListView historyListView;
    ApiService apiService;
    Context context;

    public static HistoryFragment newInstance(Context context) {
        HistoryFragment fragment = new HistoryFragment(context);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public HistoryFragment(Context context) {
        apiService = ApiService.getInstance();
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        getHistoryButton = view.findViewById(R.id.getHistory);
        clearHistoryButton = view.findViewById(R.id.clearHistory);
        historyListView = view.findViewById(R.id.historyList);
        getHistoryButton.setOnClickListener(v -> getHistory());
        clearHistoryButton.setOnClickListener(v -> clearHistory());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getHistory();
    }

    private void getHistory() {
        List<String> history = apiService.getHistory();
        if (history.isEmpty()) {
            history.add(getString(R.string.history_empty));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_list_item_1, history
        );
        historyListView.setAdapter(adapter);
    }

    private void clearHistory() {
        apiService.clearHistory();
        getHistory();
    }



}