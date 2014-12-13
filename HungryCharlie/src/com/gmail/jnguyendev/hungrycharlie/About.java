package com.gmail.jnguyendev.hungrycharlie;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Displays information about this application.
 * 
 * @author Anthony Weems, Johnny Nguyen, Jonathan Lee, Kevin Fu, Connie Shen
 * @version 1.0
 */
public class About extends Activity
{
	private TextView aboutText;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Typeface typeGoodDog = Typeface.createFromAsset(getAssets(), "GoodDog.otf");
        aboutText = (TextView) findViewById(R.id.about_page);
        aboutText.setTypeface(typeGoodDog);
    }
}
