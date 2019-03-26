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
import android.widget.TextView;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.workoutintervalapp.CustomTimerActivity;
import com.mg.workoutintervalapp.R;

public class LoadCustomWorkoutDialog extends AppCompatDialogFragment {
    private TextView workoutNameTV;
    private TextView workoutTimeTV;
    private TextView workoutRestTV;
    private TextView workoutDateTV;
    private TextView workoutIntervalsTV;
    private CustomCircuitTO selectedItem;
    private LoadWorkoutDialogListener listener;
    private Intent TimerIntent;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_loadworkout_dialog, null);
        TimerIntent = new Intent(getContext(), CustomTimerActivity.class);

        setDialogTextView(view);
        selectedItem = listener.dialogListener();
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
                        putExtrasForTimerIntent(selectedItem, TimerIntent);
                        startActivity(TimerIntent);
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (LoadWorkoutDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement LoadWorkoutDialog");
        }
    }

    private void putExtrasForTimerIntent(CustomCircuitTO item, Intent intent) {
        intent.putExtra("customCircuitTO", item);
    }

    public void addDialogVariables(CustomCircuitTO dataBaseViewItem) {
        workoutNameTV.setText("Workout Name: " + dataBaseViewItem.getName());
        workoutTimeTV.setVisibility(View.GONE);
        workoutRestTV.setVisibility(View.GONE);
        workoutIntervalsTV.setText("Intervals: " + dataBaseViewItem.getintervalToList().size());
        workoutDateTV.setText("Date Created: " + dataBaseViewItem.getDate());
    }

    private void setDialogTextView(View view) {
        workoutNameTV = view.findViewById(R.id.load_dialog_nametv);
        workoutTimeTV = view.findViewById(R.id.load_dialog_workouttimetv);
        workoutRestTV = view.findViewById(R.id.load_dialog_workoutresttv);
        workoutIntervalsTV = view.findViewById(R.id.load_dialog_workoutintervalstv);
        workoutDateTV = view.findViewById(R.id.load_dialog_workoutdatetv);
    }

    public interface LoadWorkoutDialogListener {
        CustomCircuitTO dialogListener();
    }
}
