package ph.ridefind.android.activity;

import android.os.Bundle;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import ph.ridefind.android.R;
import ph.ridefind.android.adapter.FeedListAdapter;
import ph.ridefind.android.model.Feed;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.feed_list)
public class FeedListFragment extends SherlockFragment {
    private FeedListAdapter adapter;

    @ViewById
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Feed> feeds = new ArrayList<Feed>();
        String url = "http://photos-b.ak.fbcdn.net/hphotos-ak-ash3/533540_380259988734115_1951594686_s.jpg";
        feeds.add(Feed.buildFeed(getString(R.string.lorem), url));
        feeds.add(Feed.buildFeed(getString(R.string.lorem), url));
        feeds.add(Feed.buildFeed(getString(R.string.lorem), url));
        feeds.add(Feed.buildFeed(getString(R.string.lorem), url));

        adapter = new FeedListAdapter(getActivity(), feeds);
    }

    @AfterViews
    void afterViews() {
        list.setAdapter(adapter);
    }

    @ItemClick
    void list(int position) {
    }

}
