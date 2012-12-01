package ph.ridefind.android.service;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.res.StringRes;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ph.ridefind.android.R;
import ph.ridefind.android.model.Category;
import ph.ridefind.android.model.Feed;
import ph.ridefind.android.model.Tip;

import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.*;
import static java.util.Arrays.*;

@EBean
public class WebServiceImpl implements WebService {
    private static final String TAG = WebServiceImpl.class.toString();
    private static RestTemplate restTemplate;

    @StringRes(R.string.api_root)
    String apiRoot;

    private static final String CATEGORIES = "http://{0}/api/malls";

    private static final String FEEDS = "http://{0}/api/malls/{1}/feeds";
    private static final String FEED = "http://{0}/api/feeds/{1}";

    private static final String TIPS = "http://{0}/api/malls/{1}/tips";
    private static final String TIP = "http://{0}/api/tips/{1}";


    @Override
    public List<Category> getCategories() {
        Category[] categories = getRestTemplate().getForObject(format(CATEGORIES, apiRoot), Category[].class);
        return new ArrayList<Category>(asList(categories));
    }

    @Override
    public Category getCategory(Long id) {
        return null;
    }

    @Override
    public List<Feed> getFeeds(String fsqId) {
        Feed[] feeds = getRestTemplate().getForObject(format(FEEDS, apiRoot, fsqId), Feed[].class);
        return new ArrayList<Feed>(asList(feeds));
    }

    @Override
    public Feed getFeed(Long id) {
        return getRestTemplate().getForObject(format(FEED, apiRoot, id), Feed.class);
    }

    @Override
    public List<Tip> getTips(String fsqId) {
        Tip[] tips = getRestTemplate().getForObject(format(TIPS, apiRoot, fsqId), Tip[].class);
        return new ArrayList<Tip>(asList(tips));
    }

    @Override
    public Tip getTip(Long id) {
        return getRestTemplate().getForObject(format(TIP, apiRoot, id), Tip.class);
    }

    private static RestTemplate getRestTemplate() {
        if (WebServiceImpl.restTemplate != null) {
            return restTemplate;
        } else {
            WebServiceImpl.restTemplate = createRestTemplate();
            return restTemplate;
        }
    }

    private static RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters());
        return restTemplate;
    }

    private static List<HttpMessageConverter<?>> messageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        return messageConverters;
    }
}
