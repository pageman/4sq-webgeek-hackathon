package ph.ridefind.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import ph.ridefind.android.R;
import ph.ridefind.android.UserPreferences_;

@EActivity
public class SplashActivity extends Activity {
    private Dialog splashDialog = null;

    @Pref
    UserPreferences_ preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean loggedIn = preferences.loggedIn().get();
        if (loggedIn) {
            next();
            finish();
        }
    }

    @Click
    void splash() {
        login();
    }

    private void next() {
        Intent i = new Intent(this, MainActivity_.class);
        startActivity(i);
    }

    private void login() {
        Intent i = new Intent(this, ActivityWebView_.class);
        startActivity(i);
    }
}
