package ph.ridefind.android;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gcm.GCMBaseIntentService;
import com.googlecode.androidannotations.annotations.EService;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EService
public class GcmIntentService extends GCMBaseIntentService {
    private static String TAG = GcmIntentService.class.getSimpleName();

    @Pref
    UserPreferences_ preferences;

    @SystemService
    NotificationManager notificationManager;

    protected GcmIntentService() {
        super(TAG);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
    }

    @Override
    protected void onError(Context context, String errorId) {
        Log.e(TAG, "Gcm error " + errorId);
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.d(TAG, "onRegistered " + registrationId);
        preferences.edit().registrationId().put(registrationId).apply();
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.d(TAG, "onUnregistered");
        preferences.clear();
    }
}
