package ph.ridefind.android;

import android.content.Context;
import com.google.android.gcm.GCMBroadcastReceiver;

public class GcmReceiver extends GCMBroadcastReceiver {
    @Override
    protected String getGCMIntentServiceClassName(Context context) {
        return "ph.ridefind.android.GcmIntentService";
    }
}
