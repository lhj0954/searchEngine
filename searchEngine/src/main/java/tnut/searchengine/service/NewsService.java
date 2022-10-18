package tnut.searchengine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tnut.searchengine.helper.Indices;
import tnut.searchengine.models.Dart;
import tnut.searchengine.models.News;
import tnut.searchengine.search.util.SearchUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NewsService {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RestHighLevelClient client;

    @Autowired
    public NewsService(RestHighLevelClient client) {
        this.client = client;
    }

    @Transactional(readOnly = true)
    public List<News> newsSearch(String search) {
        SearchRequest request = SearchUtil.buildSearchRequest(Indices.NEWS_INDEX, search);

        return searchInternal(request);
    }

    private List<News> searchInternal(SearchRequest request) {
        if (request == null) {
            return Collections.emptyList();
        }

        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            SearchHit[] searchHits = response.getHits().getHits();
            List<News> news = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                news.add(MAPPER.readValue(hit.getSourceAsString(), News.class));
            }

            return news;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
