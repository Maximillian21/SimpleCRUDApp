package com.maxym.crudapplicationmvp.common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maxym.crudapplicationmvp.R;
import com.maxym.crudapplicationmvp.presenter.BasePresenter;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    BasePresenter presenter;
    ArrayList<DBItem> data = new ArrayList<>();

    public RecordAdapter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<DBItem> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ImageButton ibDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.tv_person_name);
            ibDelete = (ImageButton) itemView.findViewById(R.id.ibDelete);
            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("kekw", String.valueOf(getAdapterPosition()));
                    notifyItemRemoved(getAdapterPosition());
                    presenter.delById(getAdapterPosition());
                }
            });
        }

        void bind(DBItem item) {
            nameView.setText("Name: " + item.getName() + "; Email: " + item.getEmail());
        }
    }
}
