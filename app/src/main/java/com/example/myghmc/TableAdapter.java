package com.example.myghmc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<house_data> dataList;

    public TableAdapter(List<house_data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        house_data model = dataList.get(position);
        holder.houseno.setText(model.getHouse_no());
        holder.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView houseno, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            houseno = itemView.findViewById(R.id.tvColumn9);
            time = itemView.findViewById(R.id.hjkl);
        }
    }
}
