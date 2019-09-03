package com.example.footballscoreapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FootballAdapter extends RecyclerView.Adapter<FootballAdapter.FootballViewHolder> {
    private ArrayList<ModelJadwal> dataList;
    public FootballAdapter(ArrayList<ModelJadwal> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public FootballViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row_football, parent, false);
        return new FootballViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FootballViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getStrAwayTeam());
        holder.txtNpm.setText(dataList.get(position).getStrHomeTeam());
        holder.txtNoHp.setText(dataList.get(position).getStrDate());

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FootballViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm, txtNoHp;

        public FootballViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_strAwayTeam);
            txtNpm = (TextView) itemView.findViewById(R.id.txt_strHomeTeam);
            txtNoHp = (TextView) itemView.findViewById(R.id.txt_strDate);

        }
    }


}
