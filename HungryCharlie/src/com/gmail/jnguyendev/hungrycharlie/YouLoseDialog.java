package com.gmail.jnguyendev.hungrycharlie;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.gmail.jnguyendev.hungrycharlie.R;
import android.os.Bundle;

@SuppressLint("NewApi")
public class YouLoseDialog extends DialogFragment {
	
	private static YouLoseDialog youLoseDialog;
	
	private YouLoseDialog() {
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.you_lose, null))
    		   .setNeutralButton("Next", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   DialogFragment fragment = PockyFactsDialog.getInstance();
    				   fragment.show(getFragmentManager(), "dialog");
    			   }
    		   })
    		   .setCancelable(false);

    	AlertDialog dialog = builder.create();
    	
    	return dialog;
 
    }
    
    public static YouLoseDialog getInstance() {
    	if (youLoseDialog == null) {
    		youLoseDialog = new YouLoseDialog();
    	    
        }    
        return youLoseDialog;
    }
    
    public void showDialog() {
        DialogFragment newFragment = YouLoseDialog.getInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}