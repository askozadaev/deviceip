package ru.akozadaev.deviceiphistory.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.akozadaev.deviceiphistory.ApiService;
import ru.akozadaev.deviceiphistory.R;


public class MainFragment extends Fragment {

    TextView actualAddressView;
    Button updateAddressButton;
    TextView lastAddressView;
    ApiService apiService;


    public MainFragment() {
        apiService = ApiService.getInstance();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        actualAddressView = view.findViewById(R.id.actualIpAddressView);
        lastAddressView = view.findViewById(R.id.lastIpAddressView);
        updateAddressButton = view.findViewById(R.id.updateIpAddress);
        setActualAddress(getString(R.string.unknown));
        setLastAddress(getString(R.string.unknown));
        updateAddressButton.setOnClickListener(v -> clickUpdateAddress());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateLastIp();
    }

    @SuppressLint("SetTextI18n")
    public void setActualAddress(String ip) {
        actualAddressView.setText(getString(R.string.actual_ip) + " " + ip);
    }

    @SuppressLint("SetTextI18n")
    public void setLastAddress(String ip) {
        lastAddressView.setText(getString(R.string.last_Ip) + " " + ip);
    }

    public void updateLastIp() {
        String lastIp = apiService.getLastIp();
        if (lastIp.length() != 0) {
            setLastAddress(lastIp);
        }
    }

    private void clickUpdateAddress() {
        setActualAddress(getString(R.string.loading));
        String actualAddress = apiService.getIp();
        if (actualAddress.length() != 0) {
            setActualAddress(actualAddress);
            updateLastIp();
        } else {
            setActualAddress(getString(R.string.failed_get_ip));
        }
    }
}