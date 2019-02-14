package com.mg.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mg.workoutintervalapp.R;

public class SaveSimpleWorkoutDialog extends AppCompatDialogFragment {
    private EditText editTextWorkoutName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_savesimpleworkout_dialog, null);

        builder.setView(view)
                .setTitle("Save Workout")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //save data to database
                    }
                });

        editTextWorkoutName = view.findViewById(R.id.edit_workoutname);
        return builder.create();
    }
}
