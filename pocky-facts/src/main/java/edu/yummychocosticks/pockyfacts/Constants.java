package edu.yummychocosticks.pockyfacts;

/**
 * Contains the client IDs and scopes for allowed clients consuming your API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "891572910799-5rca7mf4uludmfuqhput4a64i30eoa7c.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = WEB_CLIENT_ID;
  public static final String IOS_CLIENT_ID = WEB_CLIENT_ID;
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
