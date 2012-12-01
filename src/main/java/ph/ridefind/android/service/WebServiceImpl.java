package ph.ridefind.android.service;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.res.StringRes;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ph.ridefind.android.R;
import ph.ridefind.android.model.Category;

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

    @Override
    public List<Category> getCategories() {
        Category[] categories = getRestTemplate().getForObject(format(CATEGORIES, apiRoot), Category[].class);
        return new ArrayList<Category>(asList(categories));
    }

    @Override
    public Category getCategory(Long id) {
        return null;
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
