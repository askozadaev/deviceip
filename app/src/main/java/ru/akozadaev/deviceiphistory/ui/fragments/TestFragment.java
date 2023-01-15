package ru.akozadaev.deviceiphistory.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.akozadaev.deviceiphistory.ApiService;
import ru.akozadaev.deviceiphistory.R;

public class TestFragment extends Fragment {

    private Button addAddressButton;
    private EditText inputTextView;
    private final ApiService apiService;
    private int startIndex = 0;

    public TestFragment() {
        apiService = ApiService.getInstance();
    }

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        addAddressButton = view.findViewById(R.id.addAddress);
        inputTextView = view.findViewById(R.id.addressInput);
        inputTextView.setText(String.valueOf(startIndex));
        addAddressButton.setOnClickListener(v -> addAddress());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void addAddress() {
        String input = inputTextView.getText().toString();
        if (input.length() == 0) {
            inputTextView.setText(String.valueOf(startIndex));
            return;
        }
        try {
            Integer.parseInt(input);
            startIndex += 1;
            inputTextView.setText(String.valueOf(startIndex));
        } catch (Exception ignored) {}
        apiService.writeNewIp(input);
    }
}