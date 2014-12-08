package com.gmail.jnguyendev.hungrycharlie;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

@SuppressLint("NewApi")
public class PockyFactsFromHomeDialog extends DialogFragment {

	private static volatile PockyFactsFromHomeDialog pockyFactsDialog;

	private PockyFactsFromHomeDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setCustomTitle(inflater.inflate(R.layout.pocky_facts, null))
	           .setMessage(PockyFacts.getInstance().getFact())
	           .setPositiveButton("Another Fact", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // empty stub method
                   }
    		   })
    		   .setNegativeButton("Return Home", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   // Close popup
    				   getActivity().closeOptionsMenu();
    			   }
    		   });

    	AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				Button fact = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);
				fact.setOnClickListener(new PockyFactsListener(dialog));
			}
    	});

    	return dialog;
    }

    class PockyFactsListener implements View.OnClickListener {
        private final DialogInterface dialog;
        public PockyFactsListener(DialogInterface dialog) {
            this.dialog = dialog;
        }
        @Override
        public void onClick(View v) {
        	((AlertDialog)dialog).setMessage(PockyFacts.getInstance().getFact());
        }
    }
    
    public static PockyFactsFromHomeDialog getInstance() {
    	if (pockyFactsDialog == null) {
			synchronized (PockyFactsDialog.class) {
				if (pockyFactsDialog == null) {
					pockyFactsDialog = new PockyFactsFromHomeDialog();
				}
			}
		}

        return pockyFactsDialog;
    }
    
    public void showDialog() {
        DialogFragment newFragment = PockyFactsFromHomeDialog.getInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}