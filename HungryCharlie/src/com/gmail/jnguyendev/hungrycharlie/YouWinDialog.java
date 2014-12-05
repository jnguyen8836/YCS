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
public class YouWinDialog extends DialogFragment {
	
	private static YouWinDialog youWinDialog;
	
	private YouWinDialog() {
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.you_win, null))
    		   .setNeutralButton("Next", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   DialogFragment fragment = PockyFactsDialog.getInstance();
    				   fragment.show(getFragmentManager(), "dialog");
    			   }
    		   })
    		   ;

    	AlertDialog dialog = builder.create();
    	
    	return dialog;
 
    }
    
    public static YouWinDialog getInstance() {
    	if (youWinDialog == null) {
    		youWinDialog = new YouWinDialog();
    	    
        }    
        return youWinDialog;
    }
    
    public void showDialog() {
        DialogFragment newFragment = YouWinDialog.getInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}