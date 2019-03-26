package com.mg.database;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.workoutintervalapp.R;

import java.util.ArrayList;

public class DatabaseCustomItemAdapter extends RecyclerView.Adapter<DatabaseCustomItemAdapter.DBViewHolder> {
    private ArrayList<CustomCircuitTO> mDataBaseViewItemsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class DBViewHolder extends RecyclerView.ViewHolder{
        public TextView mWorkoutName;
        public ImageView mDeleteButton;

        public DBViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mWorkoutName = itemView.findViewById(R.id.workoutNameTV);
            mDeleteButton = itemView.findViewById(R.id.deleteButton);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), "Workout Successfully Saved!", Toast.LENGTH_LONG).show();
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), "Workout Successfully Saved!", Toast.LENGTH_LONG).show();
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public DatabaseCustomItemAdapter(ArrayList<CustomCircuitTO> dataBaseViewItemsList){
        mDataBaseViewItemsList = dataBaseViewItemsList;
    }
    @Override
    public DBViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.database_item_layout, parent, false);
        DBViewHolder dbViewHolder = new DBViewHolder(v, mListener);
        return dbViewHolder;
    }

    @Override
    public void onBindViewHolder(DBViewHolder dbViewHolder, int position) {
        CustomCircuitTO currentItem = mDataBaseViewItemsList.get(position);

        dbViewHolder.mWorkoutName.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return mDataBaseViewItemsList.size();
    }
}
