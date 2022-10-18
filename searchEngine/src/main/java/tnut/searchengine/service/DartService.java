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
import tnut.searchengine.models.Dart;
import tnut.searchengine.helper.Indices;
import tnut.searchengine.search.util.SearchUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DartService {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RestHighLevelClient client;

    @Autowired
    public DartService(RestHighLevelClient client) {
        this.client = client;
    }

    @Transactional(readOnly = true)
    public List<Dart> dartSearch(String search) {
        SearchRequest request = SearchUtil.buildSearchRequest(Indices.DART_INDEX, search);

        return searchInternal(request);
    }




    private List<Dart> searchInternal(SearchRequest request) {
        if (request == null) {
            return Collections.emptyList();
        }

        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            SearchHit[] searchHits = response.getHits().getHits();
            List<Dart> darts = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                darts.add(MAPPER.readValue(hit.getSourceAsString(), Dart.class));
            }

            return darts;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}


