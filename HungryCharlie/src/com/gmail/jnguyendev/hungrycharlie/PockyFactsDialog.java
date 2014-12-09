package com.gmail.jnguyendev.hungrycharlie;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.gmail.jnguyendev.hungrycharlie.R;
import android.os.Bundle;

@SuppressLint("NewApi")
public class PockyFactsDialog extends DialogFragment {
	
	private static volatile PockyFactsDialog pockyFactsDialog;
	private OnClickListener mGameListener = null;
	
	private PockyFactsDialog() { }
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	PockyFacts pockyFact = PockyFacts.getInstance();
    	LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setCustomTitle(inflater.inflate(R.layout.pocky_facts, null))
	           .setMessage(pockyFact.getFact())
    		   .setPositiveButton("Play Again", mGameListener)
    		   .setNegativeButton("Return Home", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   // Return to Home Screen
    				   getActivity().finish();
    			   }
    		   });

    	AlertDialog dialog = builder.create();
    	
    	return dialog;
 
    }

    public static PockyFactsDialog getInstance() {
    	if (pockyFactsDialog == null) {
			synchronized (PockyFactsDialog.class) {
				if (pockyFactsDialog == null) {
					pockyFactsDialog = new PockyFactsDialog();
				}
			}
		}

        return pockyFactsDialog;
    }

    public void setOnClickListener(DialogInterface.OnClickListener l) {
    	mGameListener = l;
    }

    public void showDialog() {
        DialogFragment newFragment = PockyFactsDialog.getInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}