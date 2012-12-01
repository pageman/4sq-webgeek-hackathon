package ph.ridefind.android.activity;

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
import ph.ridefind.android.adapter.TipListAdapter;
import ph.ridefind.android.model.Tip;
import ph.ridefind.android.service.WebService;
import ph.ridefind.android.service.WebServiceImpl;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.feed_list)
public class TipListFragment extends SherlockFragment {
    private TipListAdapter adapter;

    @Pref
    UserPreferences_ preferences;

    @ViewById
    ListView list;

    @Bean(WebServiceImpl.class)
    WebService webService;

    private static List<Tip> tips = new ArrayList<Tip>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pullTips();
    }

    @AfterViews
    void afterViews() {
        adapter = new TipListAdapter(getActivity(), tips);
        list.setAdapter(adapter);
    }

    @Background
    void pullTips() {
        boolean exists = preferences.fsqId().exists();

        if (!exists) return;

        String fsqId = preferences.fsqId().get();

        tips = webService.getTips(fsqId);
        showTips(tips);
    }

    @UiThread
    void showTips(List<Tip> tips) {
        adapter = new TipListAdapter(getActivity(), tips);
        list.setAdapter(adapter);
    }

    @ItemClick
    void list(int position) {
    }
}
