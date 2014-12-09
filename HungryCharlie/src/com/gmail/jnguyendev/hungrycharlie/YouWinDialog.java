package com.gmail.jnguyendev.hungrycharlie;

import java.text.SimpleDateFormat;

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
public class YouWinDialog extends DialogFragment {
	
	private static YouWinDialog youWinDialog;
	private OnClickListener mGameListener;
	private String mMessage;
	
	private YouWinDialog() {
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setCustomTitle(inflater.inflate(R.layout.you_win, null))
    		   .setMessage(mMessage)
    		   .setNeutralButton("Next", new DialogInterface.OnClickListener() {
    			   public void onClick(DialogInterface dialog, int id) {
    				   PockyFactsDialog fragment = PockyFactsDialog.getInstance();
    				   fragment.setOnClickListener(mGameListener);
    				   fragment.setCancelable(false);
    				   fragment.show(getFragmentManager(), "dialog");
    			   }
    		   });

    	AlertDialog dialog = builder.create();
    	
    	return dialog;
 
    }
    
    public static YouWinDialog getInstance() {
    	if (youWinDialog == null) {
    		youWinDialog = new YouWinDialog();
        }    
        return youWinDialog;
    }

    public void setOnClickListener(DialogInterface.OnClickListener l) {
    	mGameListener = l;
    }

    public void showDialog() {
        DialogFragment newFragment = YouWinDialog.getInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }

	public void setScore(long currentTime) {
		mMessage = "Score: " + (new SimpleDateFormat("mm:ss.SSS")).format(currentTime);
	}
    
}