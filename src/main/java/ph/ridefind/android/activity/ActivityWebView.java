/**
 * Copyright 2011 Mark Wyszomierski
 */
package ph.ridefind.android.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import ph.ridefind.android.R;
import ph.ridefind.android.UserPreferences_;

/**
 * https://developer.foursquare.com/docs/oauth.html
 * https://foursquare.com/oauth/
 *
 * @author Mark Wyszomierski (markww@gmail.com)
 * @date May 17, 2011
 */

@EActivity
public class ActivityWebView
        extends Activity {
    private static final String TAG = "ActivityWebView";

    @Pref
    UserPreferences_ preferences;

    /**
     * Get these values after registering your oauth app at: https://foursquare.com/oauth/
     */
    public static final String CALLBACK_URL = "https://afternoon-coast-6940.herokuapp.com";
    public static final String CLIENT_ID = "F2EMPW0AJMYPE1IXU15RX1LOEHSE4MC2SLGS2B1MQGVASQMA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String url = "https://foursquare.com/oauth2/authenticate"
                + "?client_id="
                + CLIENT_ID
                + "&response_type=token"
                + "&redirect_uri="
                + CALLBACK_URL;

        // If authentication works, we'll get redirected to a url with a pattern like:
        //
        //    http://YOUR_REGISTERED_REDIRECT_URI/#access_token=ACCESS_TOKEN
        //
        // We can override onPageStarted() in the web client and grab the token out.
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String fragment = "#access_token=";
                int start = url.indexOf(fragment);
                if (start > -1) {
                    // You can use the accessToken for api calls now.
                    String accessToken = url.substring(start
                            + fragment.length(),
                            url.length());

                    Log.v(TAG, "OAuth complete, token: [" + accessToken + "].");

                    Toast.makeText(ActivityWebView.this,
                            "Token: " + accessToken,
                            Toast.LENGTH_SHORT).show();
                    preferences.edit().loggedIn().put(true).apply();
                    finish();
                }
            }
        });
        webview.loadUrl(url);
    }
}
