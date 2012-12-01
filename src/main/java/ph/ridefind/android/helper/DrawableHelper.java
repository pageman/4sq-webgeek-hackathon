package ph.ridefind.android.helper;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class DrawableHelper {
    private static final String TAG = DrawableHelper.class.getSimpleName();

    public static Drawable getDrawableFromURL(String url) {
        try {
            URL imageUrl = new URL(url);
            InputStream content = (InputStream) imageUrl.getContent();
            return Drawable.createFromStream(content, "src");
        } catch (Exception e) {
            Log.e(TAG, e.getStackTrace().toString());
        }
        return null;
    }
}
