package com.gmail.jnguyendev.hungrycharlie;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class MainActivity extends Activity implements
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener
{
	private Button btnHungryCharlie;
	private Button btnPockyStats;
	private Button btnPockyFacts;
	private Button btnSignIn;

	private GoogleApiClient mGoogleApiClient;
    final String TAG = "HungryCharlie";
	private enum ResponseCode { RC_SIGNIN };

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

        // Create the Google API Client with access to Plus and Games
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                // unlock the following method for $25
                //.addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
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
		showDialog();
	}
	
	private void showDialog() {
		DialogFragment fragment = PockyFactsDialog.newInstance();
		fragment.show(getFragmentManager(), "dialog");
	}
	
	public void openSignIn(View view) throws IOException {
		if (isSignedIn()) {
			mGoogleApiClient.disconnect();
			btnSignIn.setText("Sign In");
		} else {
			mGoogleApiClient.connect();
		}
	}

    private boolean isSignedIn() {
        return (mGoogleApiClient != null && mGoogleApiClient.isConnected());
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        switch (ResponseCode.values()[requestCode]) {
	        case RC_SIGNIN:
	            Log.d(TAG, "onActivityResult with requestCode == RC_SIGN_IN, responseCode="
	                + responseCode + ", intent=" + intent);
	            if (responseCode == RESULT_OK) {
		            mGoogleApiClient.connect();
	            }
	    }

    }

	@Override
	public void onConnectionFailed(ConnectionResult result) {
        Log.d(TAG, "onConnectionFailed(): attempting to resolve: " + result.getErrorCode());
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, ResponseCode.RC_SIGNIN.ordinal());
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mGoogleApiClient.connect();
            }
        } else {
            // not resolvable... so show an error message
            int errorCode = result.getErrorCode();
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this, ResponseCode.RC_SIGNIN.ordinal());
            if (dialog != null) {
                dialog.show();
            }
        }
	}

	@Override
	public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "onConnected(): connected to Google APIs");
        String displayName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Log.w(TAG, "Current user is: " + displayName);
        btnSignIn.setText("Sign Out");
	}

	@Override
	public void onConnectionSuspended(int cause) {
        Log.d(TAG, "onConnectionSuspended(): attempting to connect");
        mGoogleApiClient.connect();
	}
	
}
