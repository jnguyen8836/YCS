package com.gmail.jnguyendev.hungrycharlie;

import com.gmail.jnguyendev.hungrycharlie.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

public class PockyStats extends Activity {
    /** Called when the activity is first created. */
	private Spinner len_spinner;
	private Spinner wght_spinner;
	private Spinner prce_spinner;
	private Button length_button;
	private Button weight_button;
	private Button price_button;
	private EditText stats_text;
	private TextView stats_output;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pockystats);
        addListenerOnSpinner();
        addListenerOnLengthButton();
        addListenerOnWeightButton();
        addListenerOnPriceButton();
        addListenerOnInput();
        setTextOutput();
        stats_output.setText("Total Sticks: 0.0\nTotal Boxes: 0.0");
    }
    
    public void outputText(String strToOutput) {
    	stats_output.setText(strToOutput);
    }
    
    public void setTextOutput() {
    	stats_output = (TextView) findViewById(R.id.stats_output);
    }
    
    public void addListenerOnSpinner() {
    	len_spinner = (Spinner) findViewById(R.id.length_spinner);
    	wght_spinner = (Spinner) findViewById(R.id.weight_spinner);
    	prce_spinner = (Spinner) findViewById(R.id.price_spinner);
//    	length_spinner.setOnItemSelectedListener(new CustomOnSelectedListener());
    }
    
    public void addListenerOnInput() {
    	stats_text = (EditText) findViewById(R.id.stats_input);
    	
    }
    
    public void addListenerOnLengthButton() {
    	len_spinner = (Spinner) findViewById(R.id.length_spinner);
    	length_button = (Button) findViewById(R.id.length_button);
		length_button.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
    	length_button.setTextSize(20);

    	length_button.setOnClickListener(new OnClickListener() {
    		 
    		  @Override
    		  public void onClick(View v) {
    			double inputVal = getInput();
    			String lengthUnit = String.valueOf(len_spinner.getSelectedItem());
    			if (lengthUnit.equals("Inches")) {
    				double numSticks = Math.round(inputVal/5.5*10.0)/10.0;
      				double numBoxes = Math.round(inputVal/6.0*10.0)/10.0;
      				outputText("Total Sticks: " + String.valueOf(numSticks) +
      	      				"\nTotal Boxes: " + String.valueOf(numBoxes));
//      				Toast.makeText(PockyStats.this, "INCHES: " +
//      				"Total Sticks: " + String.valueOf(numSticks) +
//      				"\nTotal Boxes: " + String.valueOf(numBoxes) +
//      			  	"\nLength Spinner : "+ lengthUnit,
//      			  	Toast.LENGTH_SHORT).show();
    			}
    			else if (lengthUnit.equals("Centimeters")) {
    				double inputInches = inputVal/0.393701;
      				double numSticks = Math.round(inputInches/5.5*10.0)/10.0;
      				double numBoxes = Math.round(inputInches/6.0*10.0)/10.0;
      				outputText("Total Sticks: " + String.valueOf(numSticks) +
      	      		  		"\nTotal Boxes: " + String.valueOf(numBoxes));
//      				Toast.makeText(PockyStats.this, "CENTIMETERS: " +
//      				"Total Sticks: " + String.valueOf(numSticks) +
//      		  		"\nTotal Boxes: " + String.valueOf(numBoxes) +
//      			  	"\nLength Spinner : "+ lengthUnit,
//      			  	Toast.LENGTH_SHORT).show();
    			}

    		  }
    	 
    		});
    }
    public void addListenerOnWeightButton() {
    	wght_spinner = (Spinner) findViewById(R.id.weight_spinner);
    	weight_button = (Button) findViewById(R.id.weight_button);
		weight_button.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
    	weight_button.setTextSize(20);

    	weight_button.setOnClickListener(new OnClickListener() {
   		 
  		  @Override
  		  public void onClick(View v) {
  			double inputVal = getInput();
  			String weightUnit = String.valueOf(wght_spinner.getSelectedItem());
  			if (weightUnit.equals("Grams")) {
  				double numSticks = Math.round(inputVal/2.46*10.0)/10.0;
  				double numBoxes = Math.round(inputVal/70.0*10.0)/10.0;
  				outputText("Total Sticks: " + String.valueOf(numSticks) +
  		  				"\nTotal Boxes: " + String.valueOf(numBoxes));
//  				Toast.makeText(PockyStats.this, "GRAMS: " +
//  				"Total Sticks: " + String.valueOf(numSticks) +
//  				"\nTotal Boxes: " + String.valueOf(numBoxes) +
//  			  	"\nWeight Spinner : "+ weightUnit,
//  			  	Toast.LENGTH_SHORT).show();
  			}
  			else if(weightUnit.equals("Ounces")) {
  				double inputGrams = inputVal*28.3495;
  				double numSticks = Math.round(inputGrams/2.46*10.0)/10.0;
  				double numBoxes = Math.round(inputGrams/70.0*10.0)/10.0;
  				outputText("Total Sticks: " + String.valueOf(numSticks) +
  		  		  		"\nTotal Boxes: " + String.valueOf(numBoxes));
//  				Toast.makeText(PockyStats.this, "OUNCES: " +
//  				"Total Sticks: " + String.valueOf(numSticks) +
//  		  		"\nTotal Boxes: " + String.valueOf(numBoxes) +
//  			  	"\nWeight Spinner : "+ weightUnit,
//  			  	Toast.LENGTH_SHORT).show();
  			}
  		    
  		  }
  	 
  		});    	
    }
    
    public void addListenerOnPriceButton() {
    	prce_spinner = (Spinner) findViewById(R.id.price_spinner);
    	price_button = (Button) findViewById(R.id.price_button);    
    	price_button.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
    	price_button.setTextSize(20);
    	price_button.setOnClickListener(new OnClickListener() {
      		 
    		  @Override
    		  public void onClick(View v) {
      			double inputVal = getInput();
      			String priceUnit = String.valueOf(prce_spinner.getSelectedItem());
      			
    			if (priceUnit.equals("Dollars")) {
    				double numSticks = Math.round(inputVal/0.059*10.0)/10.0;
      				double numBoxes = Math.round(inputVal/2.25*10.0)/10.0;
      				outputText("Total Sticks: " + String.valueOf(numSticks) +
      	      				"\nTotal Boxes: " + String.valueOf(numBoxes));
//      				Toast.makeText(PockyStats.this, "YEN: " +
//      				"Total Sticks: " + String.valueOf(numSticks) +
//      				"\nTotal Boxes: " + String.valueOf(numBoxes) +
//      			  	"\nPrice Spinner : "+ priceUnit,
//      			  	Toast.LENGTH_SHORT).show();
    			}
    			else if (priceUnit.equals("Yen")) {
    				double inputInches = inputVal*0.0084;
      				double numSticks = Math.round(inputInches/0.059*10.0)/10.0;
      				double numBoxes = Math.round(inputInches/2.25*10.0)/10.0;
      				outputText("Total Sticks: " + String.valueOf(numSticks) +
      	      		  		"\nTotal Boxes: " + String.valueOf(numBoxes));
//      				Toast.makeText(PockyStats.this, "DOLLARS: " +
//      				"Total Sticks: " + String.valueOf(numSticks) +
//      		  		"\nTotal Boxes: " + String.valueOf(numBoxes) +
//      			  	"\nPrice Spinner : "+ priceUnit,
//      			  	Toast.LENGTH_SHORT).show();
    			}
    		  }
    	 
    		});  
    }
    
    public double getInput() {
    	return Double.parseDouble(stats_text.getText().toString());
    }
}
