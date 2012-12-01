package ph.ridefind.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gcm.GCMRegistrar;
import org.apache.commons.lang3.StringUtils;
import ph.ridefind.android.activity.CategoryListActivity_;

public class MainActivity extends Activity {

    
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
