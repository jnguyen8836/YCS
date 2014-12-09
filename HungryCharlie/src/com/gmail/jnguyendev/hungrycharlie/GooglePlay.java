package com.gmail.jnguyendev.hungrycharlie;

import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

public class GooglePlay {
	private static final String LEADERBOARD = "CgkI3dmynLAbEAIQCA";
	private static final String ACHIEVEMENT_03MIN = "CgkI3dmynLAbEAIQAQ";
	private static final String ACHIEVEMENT_01MIN = "CgkI3dmynLAbEAIQAg";
	private static final String ACHIEVEMENT_30SEC = "CgkI3dmynLAbEAIQAw";
	private static final String ACHIEVEMENT_10SEC = "CgkI3dmynLAbEAIQBA";
	private static final String ACHIEVEMENT_05SEC = "CgkI3dmynLAbEAIQBQ";

	private static volatile GooglePlay instance = null;
	private GoogleApiClient mApi;

	private GooglePlay() { }

	public static GooglePlay getInstance() {
		if (instance == null) {
			synchronized (GooglePlay.class) {
				if (instance == null) {
					instance = new GooglePlay();
				}
			}
		}

		return instance;
	}
	
	public void setApi(GoogleApiClient api) {
		mApi = api;
	}
	
	public GoogleApiClient getApi() {
		return mApi;
	}

	public boolean isSignedIn() {
        return (mApi != null && mApi.isConnected());
    }

	public void submitScore(long l) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScoreImmediate(mApi, LEADERBOARD, l);
			if (l <= 5000) Games.Achievements.unlock(mApi, ACHIEVEMENT_05SEC);
			if (l <= 10000) Games.Achievements.unlock(mApi, ACHIEVEMENT_10SEC);
			if (l <= 30000) Games.Achievements.unlock(mApi, ACHIEVEMENT_30SEC);
			if (l <= 60000) Games.Achievements.unlock(mApi, ACHIEVEMENT_01MIN);
			if (l <= 180000) Games.Achievements.unlock(mApi, ACHIEVEMENT_03MIN);
		}
	}

	public Intent showLeaderboard() {
		return Games.Leaderboards.getAllLeaderboardsIntent(mApi);
	}

}
