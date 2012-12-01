package ph.ridefind.android.service;

import ph.ridefind.android.model.Category;
import ph.ridefind.android.model.Feed;
import ph.ridefind.android.model.Tip;

import java.util.List;

public interface WebService {
    List<Category> getCategories();

    Category getCategory(Long id);

    List<Feed> getFeeds(String fsqId);

    Feed getFeed(Long id);

    List<Tip> getTips(String fsqId);

    Tip getTip(Long id);
}
