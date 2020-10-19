package com.example.hw1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView2 = inflater.inflate(R.layout.fragment2,container,false);
        TextView textView = rootView2.findViewById(R.id.textView);
        Bundle bundle = this.getArguments();
        textView.setText(Integer.toString(bundle.getInt("key")));
        if (bundle.getInt("key")%2 == 0) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.CYAN);
        }
        return rootView2;
    }
}
