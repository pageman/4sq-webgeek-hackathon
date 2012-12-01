package ph.ridefind.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gcm.GCMBaseIntentService;
import com.googlecode.androidannotations.annotations.EService;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.res.StringRes;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import ph.ridefind.android.activity.MainActivity_;

import static java.text.MessageFormat.*;

@EService
public class GcmIntentService extends GCMBaseIntentService {
    private static String TAG = GcmIntentService.class.getSimpleName();
    private static final int __ID = 1;

    @Pref
    UserPreferences_ preferences;

    @SystemService
    NotificationManager notificationManager;

    @StringRes
    String title;

    @StringRes
    String info;

    protected GcmIntentService() {
        super(TAG);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        notify(intent.getExtras());
    }

    private void notify(Bundle bundle) {
        Log.e(TAG, "Got this " + bundle);

        int id = Integer.parseInt(bundle.getString("id"));
        String name = bundle.getString("name");

        // Instantiate notif
        Notification notification = new Notification(R.drawable.icon, title, System.currentTimeMillis());
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;

        // Define message
        Intent notificationIntent = new Intent(this, MainActivity_.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        notification.setLatestEventInfo(getApplicationContext(), title, format(info, name), contentIntent);

        // Pass notif to manager
        notificationManager.notify(__ID, notification);
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
