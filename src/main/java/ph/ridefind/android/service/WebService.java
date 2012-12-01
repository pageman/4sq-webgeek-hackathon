package ph.ridefind.android.service;

import ph.ridefind.android.model.Category;

import java.util.List;

public interface WebService {
    List<Category> getCategories();

    Category getCategory(Long id);
}
