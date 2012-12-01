package ph.ridefind.android.activity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.R;
import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;
import ph.ridefind.android.adapter.TipListAdapter;
import ph.ridefind.android.model.Tip;

@EActivity(R.layout.tip_view)
public class TipViewActivity extends SherlockActivity {
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
        Tip tip = TipListFragment_.tips.get(id);

        if (tip.getName() != null) {
            title.setText(tip.getName());
        }

        if (tip.getDesc() != null) {
            desc.setText(tip.getDesc());
        }

        if (tip.getImageUrl() != null) {
            Drawable drawable = TipListAdapter.drawables.get(tip.getImageUrl());
            if (drawable != null) {
                image.setImageDrawable(drawable.getConstantState().newDrawable());
            }
        }
    }

}
