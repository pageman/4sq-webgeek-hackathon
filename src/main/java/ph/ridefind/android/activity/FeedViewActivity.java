package ph.ridefind.android.activity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.R;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import ph.ridefind.android.model.Feed;

import static ph.ridefind.android.helper.DrawableHelper.*;

@EActivity(R.layout.feed_view)
public class FeedViewActivity extends SherlockActivity {
    private static final String TAG = TipViewActivity.class.getSimpleName();

    @Extra
    Integer id;

    @ViewById
    ImageView image;

    @ViewById
    TextView title;

    @ViewById
    TextView desc;

    @AfterViews
    void afterViews() {
        Feed feed = FeedListFragment_.feeds.get(id);
        title.setText(feed.getName());
        desc.setText(feed.getDesc());
        if (feed.getImageUrl() != null) {
            pullImage(feed.getImageUrl());
        }
    }

    @Background
    void pullImage(String url) {
        Drawable drawable = getDrawableFromURL(url);
        setImage(drawable);
    }

    @UiThread
    void setImage(Drawable drawable) {
        if (drawable != null) {
            image.setImageDrawable(drawable);
        }
    }
}
