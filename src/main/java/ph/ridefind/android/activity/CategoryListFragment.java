package ph.ridefind.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import ph.ridefind.android.R;
import ph.ridefind.android.adapter.CategoryListAdapter;
import ph.ridefind.android.model.Category;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.category_list)
public class CategoryListFragment extends SherlockFragment {

    private CategoryListAdapter adapter;

    @ViewById
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Category> categories = new ArrayList<Category>();
        categories.add(Category.buildCategory(getString(R.string.lorem)));
        categories.add(Category.buildCategory(getString(R.string.lorem)));
        categories.add(Category.buildCategory(getString(R.string.lorem)));

        adapter = new CategoryListAdapter(getActivity(), categories);
    }

    @AfterViews
    void afterViews() {
        list.setAdapter(adapter);
    }

    @ItemClick
    void list(int position) {
    }
}
