package ph.ridefind.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.EActivity;
import ph.ridefind.android.R;

import java.util.ArrayList;

@EActivity
public class MainActivity extends SherlockFragmentActivity {


    private TabHost tabHost;
    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_tabs_pager);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        viewPager = (ViewPager) findViewById(R.id.pager);

        tabsAdapter = new TabsAdapter(this, tabHost, viewPager);

//        tabsAdapter.addTab(
//                tabHost.newTabSpec("hello world").setIndicator("FOOBARBAZ"),
//                CategoryListFragment_.class,
//                null
//        );

        tabsAdapter.addTab(
                tabHost.newTabSpec("tips").setIndicator("tips"),
                TipListFragment_.class,
                null
        );

        tabsAdapter.addTab(
                tabHost.newTabSpec("feeds").setIndicator("feeds"),
                FeedListFragment_.class,
                null
        );

        if (savedInstanceState != null) {
            tabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", tabHost.getCurrentTabTag());
    }

    public static class TabsAdapter extends FragmentPagerAdapter
            implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

        private final Context context;
        private final TabHost tabHost;
        private final ViewPager viewPager;
        private final ArrayList<TabInfo> tabs = new ArrayList<TabInfo>();

        static final class TabInfo {
            private final String tag;
            private final Class<?> clazz;
            private final Bundle args;

            TabInfo(String tag, Class<?> clazz, Bundle args) {
                this.tag = tag;
                this.clazz = clazz;
                this.args = args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context context;

            public DummyTabFactory(Context context) {
                this.context = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(context);
                v.setMinimumHeight(0);
                v.setMinimumWidth(0);
                return v;
            }
        }

        public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager viewPager) {
            super(activity.getSupportFragmentManager());
            this.context = activity;
            this.tabHost = tabHost;
            this.viewPager = viewPager;
            this.tabHost.setOnTabChangedListener(this);
            this.viewPager.setAdapter(this);
            this.viewPager.setOnPageChangeListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clazz, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(context));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clazz, args);
            tabs.add(info);
            tabHost.addTab(tabSpec);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int i) {
            TabInfo info = tabs.get(i);
            return Fragment.instantiate(context, info.clazz.getName(), info.args);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            TabWidget widget = tabHost.getTabWidget();
            int old = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            tabHost.setCurrentTab(i);
            widget.setDescendantFocusability(old);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }

        @Override
        public void onTabChanged(String s) {
            int position = tabHost.getCurrentTab();
            viewPager.setCurrentItem(position);
        }
    }
}
