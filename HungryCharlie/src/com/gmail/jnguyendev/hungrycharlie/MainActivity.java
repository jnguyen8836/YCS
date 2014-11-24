package com.gmail.jnguyendev.hungrycharlie;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnHungryCharlie;
	private Button btnPockyStats;
	private Button btnPockyFacts;
	private Button btnSignIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		btnHungryCharlie = (Button)findViewById(R.id.button_hungry_charlie);
		btnHungryCharlie.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
		btnHungryCharlie.setTextSize(25);
		btnPockyStats = (Button)findViewById(R.id.button_pocky_stats);
		btnPockyStats.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
		btnPockyStats.setTextSize(25);
		btnPockyFacts = (Button)findViewById(R.id.button_pocky_facts);
		btnPockyFacts.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
		btnPockyFacts.setTextSize(25);
		btnSignIn = (Button)findViewById(R.id.button_sign_in);
		btnSignIn.setTypeface(Typeface.createFromAsset(getAssets(), "GoodDog.otf"));
		btnSignIn.setTextSize(18);
	}
	
	public void openHungryCharlie(View view) throws IOException {
		Intent intent = new Intent(this, Play.class);
		startActivity(intent);
	}
	
	public void openPockyStats(View view) throws IOException {
		Intent intent = new Intent(this, About.class);
		startActivity(intent);
	}
	
	public void openPockyFacts(View view) throws IOException {
		Intent intent = new Intent(this, About.class);
		startActivity(intent);
	}
	
	public void openSignIn(View view) throws IOException {
		Intent intent = new Intent(this, About.class);
		startActivity(intent);
	}
	
}
