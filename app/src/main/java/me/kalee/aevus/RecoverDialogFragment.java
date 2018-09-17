package me.kalee.aevus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RecoverDialogFragment extends DialogFragment {

    public static RecoverDialogFragment newInstance() {
        Bundle args = new Bundle();
        RecoverDialogFragment fragment = new RecoverDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("We sent an email to you.")
                .setPositiveButton("alright", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
}
