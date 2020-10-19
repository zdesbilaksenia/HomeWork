package com.example.hw1;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final DataSource ourInstance = new DataSource();

    static DataSource getInstance() { return ourInstance; }

    private final List<cellModel> list;
    public List<cellModel> getData(){
        return list;
    }
    
    private DataSource() {
        list = new ArrayList<>();
        int number = 0; int col = 0;
        for (int i = 1; i <= 100; i++){
            number = i;
            if (i%2==0) {
                col = Color.RED;
            } else {
                col = Color.CYAN;
            }
            list.add(new cellModel(number, col));
        }
    };

    public static class cellModel {
        public int mNum;
        public int mColor;

        public cellModel(int num, int col) {
            mNum = num;
            mColor = col;
        }
    }

}
