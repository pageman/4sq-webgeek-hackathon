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
import ph.ridefind.android.model.Feed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ph.ridefind.android.helper.DrawableHelper.*;

public class FeedListAdapter extends BaseAdapter {
    private List<Feed> feeds;
    private final LayoutInflater layoutInflater;

    public static Map<String, Drawable> drawables = new HashMap<String, Drawable>();

    public FeedListAdapter(Context context, List<Feed> feeds) {
        this.feeds = feeds;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return feeds.size();
    }

    public Feed getItem(int position) {
        return feeds.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Feed feed = getItem(position);
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.feed_list_item, viewGroup, false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        if (feed.getName() != null) {
            name.setText(feed.getName());
        }

        TextView desc = (TextView) view.findViewById(R.id.desc);
        if (feed.getDesc() != null) {
            desc.setText(feed.getDesc());
        }

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(feed.getCreatedAt().toString());

        ImageView image = (ImageView) view.findViewById(R.id.thumbnail);
        String imageUrl = feed.getImageUrl();
        if (drawables.get(imageUrl) != null) {
            image.setImageDrawable(drawables.get(imageUrl));
        } else {
            new LoadContentTask().execute(image, imageUrl);
        }

        return view;
    }

    private class LoadContentTask extends AsyncTask<Object, Void, Drawable> {
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
