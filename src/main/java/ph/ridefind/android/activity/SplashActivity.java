package ph.ridefind.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import ph.ridefind.android.R;

@EActivity
public class SplashActivity extends Activity {
    private Dialog splashDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    @Click
    void splash() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
