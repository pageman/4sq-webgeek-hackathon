package ph.ridefind.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ph.ridefind.android.R;
import ph.ridefind.android.model.Category;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    private List<Category> categories;
    private final LayoutInflater layoutInflater;

    public CategoryListAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return categories.size();
    }

    public Category getItem(int position) {
        return categories.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Category category = getItem(position);
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.category_list_item, viewGroup, false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(category.getName());

        return view;
    }
}
