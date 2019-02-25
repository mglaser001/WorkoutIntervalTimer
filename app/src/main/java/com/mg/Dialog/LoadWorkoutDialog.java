package com.mg.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mg.database.DataBaseViewItems;
import com.mg.workoutintervalapp.R;
import com.mg.workoutintervalapp.SimpleTimerActivity;

public class LoadWorkoutDialog extends AppCompatDialogFragment {
    private TextView workoutNameTV;
    private TextView workoutTimeTV;
    private TextView workoutRestTV;
    private TextView workoutDateTV;
    private TextView workoutIntervalsTV;
    private LoadWorkoutDialogListener listener;
    private Intent TimerIntent;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_loadworkout_dialog, null);
        TimerIntent = new Intent(getContext(), SimpleTimerActivity.class);

        setDialogTextView(view);
        DataBaseViewItems selectedItem = listener.dialogListener();
        addDialogVariables(selectedItem);

        builder.setView(view)
                .setTitle("Workout Information")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //start workout
                        //startActivity(TimerIntent);
                    }
                });




        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (LoadWorkoutDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement LoadWorkoutDialog");
        }
    }
    public void addDialogVariables(DataBaseViewItems dataBaseViewItem){
        workoutNameTV.setText("Workout Name: " + dataBaseViewItem.getWorkoutName());
        workoutTimeTV.setText("Workout Time: " + dataBaseViewItem.getWorkoutTime());
        workoutRestTV.setText("Rest Time: " + dataBaseViewItem.getWorkoutRest());
        workoutIntervalsTV.setText("Intervals: " + dataBaseViewItem.getWorkoutIntervals());
        workoutDateTV.setText("Date Created: " + dataBaseViewItem.getWorkoutDate());
    }
    private void setDialogTextView(View view){
        workoutNameTV = view.findViewById(R.id.load_dialog_nametv);
        workoutTimeTV = view.findViewById(R.id.load_dialog_workouttimetv);
        workoutRestTV = view.findViewById(R.id.load_dialog_workoutresttv);
        workoutIntervalsTV = view.findViewById(R.id.load_dialog_workoutintervalstv);
        workoutDateTV = view.findViewById(R.id.load_dialog_workoutdatetv);
    }
    public interface LoadWorkoutDialogListener{
        DataBaseViewItems dialogListener();
    }
}
