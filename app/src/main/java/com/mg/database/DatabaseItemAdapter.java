package com.mg.database;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.workoutintervalapp.LoadTimerActivity;
import com.mg.workoutintervalapp.R;
import com.mg.workoutintervalapp.SimpleTimerMenuActivity;

import java.util.ArrayList;

public class DatabaseItemAdapter extends RecyclerView.Adapter<DatabaseItemAdapter.DBViewHolder> {
    private ArrayList<DataBaseViewItems> mDataBaseViewItemsList;
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
        public TextView mWorkoutTime;
        public Button mDeleteButton;

        public DBViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mWorkoutName = itemView.findViewById(R.id.workoutNameTV);
            mWorkoutTime = itemView.findViewById(R.id.workoutTimeTV);
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
//            mDeleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Toast.makeText(v.getContext(), "Workout Successfully Saved!", Toast.LENGTH_LONG).show();
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
    public DatabaseItemAdapter(ArrayList<DataBaseViewItems> dataBaseViewItemsList){
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
        DataBaseViewItems currentItem = mDataBaseViewItemsList.get(position);

        dbViewHolder.mWorkoutName.setText(currentItem.getWorkoutName());
        dbViewHolder.mWorkoutTime.setText(currentItem.getmWorkoutId());
    }

    @Override
    public int getItemCount() {
        return mDataBaseViewItemsList.size();
    }
}
