package ph.ridefind.android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ph.ridefind.android.R;
import ph.ridefind.android.model.Tip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ph.ridefind.android.helper.DrawableHelper.*;

public class TipListAdapter extends BaseAdapter {
    private List<Tip> tips;
    private final LayoutInflater layoutInflater;

    public static Map<String, Drawable> drawables = new HashMap<String, Drawable>();

    public TipListAdapter(Context context, List<Tip> tips) {
        this.tips = tips;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return tips.size();
    }

    public Tip getItem(int position) {
        return tips.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Tip tip = getItem(position);
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.tip_list_item, viewGroup, false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        if (tip.getName() != null) {
            name.setText(tip.getName());
        }

        TextView desc = (TextView) view.findViewById(R.id.desc);
        if (tip.getDesc() != null) {
            desc.setText(tip.getDesc());
        }

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(tip.getCreatedAt().toString());

        ImageView image = (ImageView) view.findViewById(R.id.thumbnail);
        String imageUrl = tip.getImageUrl();
        if (imageUrl != null) {
            if (drawables.get(imageUrl) != null) {
                image.setImageDrawable(drawables.get(imageUrl));
            } else {
                new X().execute(image, imageUrl);
            }
        }

        return view;
    }

    private class X extends AsyncTask<Object, Void, Drawable> {
        private ImageView view;
        private String url;

        @Override
        protected Drawable doInBackground(Object... objects) {
            view = (ImageView) objects[0];
            url = (String) objects[1];

            return getDrawableFromURL(url);
        }

        protected void onPostExecute(Drawable drawable) {
            if (drawable != null) {
                drawables.put(url, drawable);
                view.setImageDrawable(drawable);
            }
        }
    }
}
