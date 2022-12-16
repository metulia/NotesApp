package com.example.notesapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyAlertDialogFragment extends DialogFragment {

    public static final String TAG = "MyAlertDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Activity activity = requireActivity();

        return new AlertDialog.Builder(activity)
                .setTitle("Внимание!")
                .setMessage("Вы действительно хотите закрыть приложение?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                        Toast.makeText(activity, "Работа с приложением завершена",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Нет", null)
                .create();
    }
}
