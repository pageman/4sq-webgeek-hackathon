package ph.ridefind.android.application;

import android.app.Application;
import android.util.Log;
import com.google.android.gcm.GCMRegistrar;
import com.googlecode.androidannotations.annotations.EApplication;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.StringUtils;
import ph.ridefind.android.UserPreferences_;

@EApplication
public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static final String PROJECT_ID = "563385715604";

    @Pref
    UserPreferences_ preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        initGcm();
    }

    private void initGcm() {
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);

        String registrationId = GCMRegistrar.getRegistrationId(this);
        Log.d(TAG, "Was able to get this " + registrationId);
        if (StringUtils.isBlank(registrationId)) {
            GCMRegistrar.register(this, PROJECT_ID);
        } else {
            boolean registered = preferences.registrationId().exists();
            if (!registered) {
                Log.i(TAG, "You're not yet registered.");
                GCMRegistrar.unregister(this);
                GCMRegistrar.register(this, PROJECT_ID);
            }
        }
    }
}
