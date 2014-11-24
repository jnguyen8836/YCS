package com.gmail.jnguyendev.hungrycharlie;

import com.gmail.jnguyendev.hungrycharlie.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * Displays information about this application.
 * 
 * @author Dan Ruscoe (ruscoe.org)
 * @version 1.0
 */
public class About extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
}
