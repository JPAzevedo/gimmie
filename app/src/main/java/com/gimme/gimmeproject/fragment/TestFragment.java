package com.gimme.gimmeproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gimme.gimmeproject.R;

public class TestFragment extends Fragment {

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_test,container,false);
        TextView textView = (TextView) fragmentView.findViewById(R.id.tTest);
        textView.setText(R.string.hello_blank_fragment);
        return fragmentView;
    }
}
