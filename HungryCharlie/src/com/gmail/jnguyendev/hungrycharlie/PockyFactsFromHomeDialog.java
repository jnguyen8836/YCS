package com.gmail.jnguyendev.hungrycharlie;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.os.*;

@SuppressLint("NewApi")
public class PockyFactsFromHomeDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	PockyFacts pockyFact = PockyFacts.newInstance();
        
    	builder.setMessage(pockyFact.getFact())
    	       .setTitle("Random Pocky Fact!")
    		   .setPositiveButton("Again", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    	               // Get another Pocky Fact
    				   DialogFragment fragment = PockyFactsFromHomeDialog.newInstance();
    				   fragment.show(getFragmentManager(), "dialog");
    			   }
    		   })
    		   .setNegativeButton("Return Home", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   // Close popup
    				   getActivity().closeOptionsMenu();
    			   }
    		   });

    	AlertDialog dialog = builder.create();
    	
    	return dialog;
    }
    
    public static PockyFactsFromHomeDialog newInstance() {
    	PockyFactsFromHomeDialog f = new PockyFactsFromHomeDialog();
        return f;
    }
    
    public void showDialog() {
        DialogFragment newFragment = PockyFactsFromHomeDialog.newInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}