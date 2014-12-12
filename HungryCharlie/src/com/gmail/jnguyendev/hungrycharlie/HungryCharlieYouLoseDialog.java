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
public class HungryCharlieYouLoseDialog extends DialogFragment {
	
	private static HungryCharlieYouLoseDialog youLoseDialog;
	private OnClickListener mGameListener;
	
	private HungryCharlieYouLoseDialog() { }
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.you_lose, null))
    		   .setNeutralButton("Next", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   PockyFactsDialog fragment = PockyFactsDialog.getInstance();
    				   fragment.setOnClickListener(mGameListener);
    				   fragment.setCancelable(false);
    				   fragment.show(getFragmentManager(), "dialog");
    			   }
    		   })
    		   .setCancelable(false);

    	AlertDialog dialog = builder.create();
    	
    	return dialog;
 
    }
    
    public static HungryCharlieYouLoseDialog getInstance() {
    	if (youLoseDialog == null) {
    		youLoseDialog = new HungryCharlieYouLoseDialog();
    	    
        }    
        return youLoseDialog;
    }

    public void setOnClickListener(DialogInterface.OnClickListener l) {
    	mGameListener = l;
    }
    
    public void showDialog() {
        DialogFragment newFragment = HungryCharlieYouLoseDialog.getInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }
    
}