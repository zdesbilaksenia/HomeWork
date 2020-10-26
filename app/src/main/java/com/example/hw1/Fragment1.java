package com.example.hw1;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.util.List;

public class Fragment1 extends Fragment {

    int size = 0;
    List<DataSource.cellModel> cells = DataSource.getInstance().getData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1,container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler);
        if (getArguments() != null) {
            size = getArguments().getInt("size");
            if (size != 0) {
                int col = 0;
                for (int i = 101; i <= size; i++) {
                    if (i % 2 == 0) {
                        col = Color.RED;
                    } else {
                        col = Color.CYAN;
                    }
                    cells.add(new DataSource.cellModel(i, col));
                }
            }
        }
        int spanCount = getResources().getInteger(R.integer.column_counter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), spanCount));
        myAdapter adapter = new myAdapter(cells);
        recyclerView.setAdapter(adapter);
        Button addButton = rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addNum();
            }
        });

        return rootView;
    }

    public  class myAdapter extends RecyclerView.Adapter<myViewHolder> {

        private final List<DataSource.cellModel> mData;

        public myAdapter(List<DataSource.cellModel> mData) {
            this.mData = mData;
        }


        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cells_layout, parent, false);
            Button numBtn = view.findViewById(R.id.numButton);
            numBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.parseInt(String.valueOf(numBtn.getText()));
                    addFragment(num);
                }
            });
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
            DataSource.cellModel model = mData.get(position);
            holder.cell.setText(Integer.toString(model.mNum));
            holder.cell.setTextColor(model.mColor);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void addNum() {
            int number = mData.size() +1 ;
            int col = 0;
            if (number%2==0) {
                col = Color.RED;
            } else {
                col = Color.CYAN;
            }
            mData.add(new DataSource.cellModel(number, col));
            notifyItemInserted(mData.size()-1);
        }

        public void addFragment(int num){
            Bundle bundle = new Bundle();
            bundle.putInt("key", num);
            Fragment2 window = new Fragment2();
            window.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            assert fragmentManager != null;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment2Container, window);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        public Button cell;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.numButton);
        }
    }

}
