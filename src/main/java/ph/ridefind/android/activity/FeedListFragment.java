package ph.ridefind.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import ph.ridefind.android.R;
import ph.ridefind.android.UserPreferences_;
import ph.ridefind.android.adapter.FeedListAdapter;
import ph.ridefind.android.model.Feed;
import ph.ridefind.android.service.WebService;
import ph.ridefind.android.service.WebServiceImpl;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.feed_list)
public class FeedListFragment extends SherlockFragment {
    private static final String TAG = FeedListFragment.class.getSimpleName();
    private FeedListAdapter adapter;

    @Pref
    UserPreferences_ preferences;

    @ViewById
    ListView list;

    @Bean(WebServiceImpl.class)
    WebService webService;

    public static List<Feed> feeds = new ArrayList<Feed>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pullFeeds();
    }

    @AfterViews
    void afterViews() {
        adapter = new FeedListAdapter(getActivity(), feeds);
        list.setAdapter(adapter);
    }

    @Background
    void pullFeeds() {
        boolean exists = preferences.fsqId().exists();

        if (!exists) return;

        String fsqId = preferences.fsqId().get();

        feeds = webService.getFeeds(fsqId);
        showFeeds(feeds);
    }

    @UiThread
    void showFeeds(List<Feed> feeds) {
        adapter = new FeedListAdapter(getActivity(), feeds);
        list.setAdapter(adapter);
    }

    @ItemClick
    void list(int position) {
        Intent i = new Intent(getActivity(), FeedViewActivity_.class);
        i.putExtra("id", position);
        startActivity(i);
    }
}
