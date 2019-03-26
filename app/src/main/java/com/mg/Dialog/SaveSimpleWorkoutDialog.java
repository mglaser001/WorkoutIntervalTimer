package com.mg.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mg.workoutintervalapp.R;

public class SaveSimpleWorkoutDialog extends AppCompatDialogFragment {
    private EditText editTextWorkoutName;
    private SimpleWorkoutDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
                        String workoutName = editTextWorkoutName.getText().toString();
                        if (workoutName.equals("")) {
                            Toast.makeText(getActivity(), "Please Enter A Name!", Toast.LENGTH_LONG).show();
                        } else {
                            listener.applyToDatabase(workoutName);
                        }
                    }
                });

        editTextWorkoutName = view.findViewById(R.id.edit_workoutname);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SimpleWorkoutDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement SimpleworkoutDialogListener");
        }
    }

    public interface SimpleWorkoutDialogListener {
        void applyToDatabase(String workoutName);
    }
}
