package com.gmail.jnguyendev.hungrycharlie;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.os.*;

@SuppressLint("NewApi")
public class PockyFactsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	PockyFacts pockyFact = PockyFacts.newInstance();
        
    	builder.setMessage(pockyFact.getFact())
    	       .setTitle("Random Pocky Fact!")
    		   .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    	               // Restart Hungry Charlie!
    				   Intent intent = new Intent(getActivity(), HungryCharlie.class);
    				   getActivity().finish();
    				   startActivity(intent);
    			   }
    		   })
    		   .setNegativeButton("Return to Home", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   // Return to Home Screen
    				   getActivity().finish();
    			   }
    		   });

    	AlertDialog dialog = builder.create();
    	dialog.setCanceledOnTouchOutside(true);
    	
    	return dialog;
    }
    
    public static PockyFactsDialog newInstance() {
    	PockyFactsDialog f = new PockyFactsDialog();
        return f;
    }
    
    public void showDialog() {
        DialogFragment newFragment = PockyFactsDialog.newInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}