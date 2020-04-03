package com.example.myapplication.material_design_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    private Context context;

    public DemoAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_demo,viewGroup,false);
        DemoViewHolder holder = new DemoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class DemoViewHolder extends RecyclerView.ViewHolder{

        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
