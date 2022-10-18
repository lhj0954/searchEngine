package tnut.searchengine.search.util;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.CollectionUtils;
import tnut.searchengine.search.SearchRequestDTO;

import java.util.List;

public class SearchUtil {

    private SearchUtil() {};

    //-----------------------------------------------------<쿼리 빌더>---------------------------------------------------------
    public static QueryBuilder getQueryBuilder(SearchRequestDTO dto) {
        if (dto ==  null) {
            return null;
        }

        List<String> fields = dto.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        if (fields.size() > 1) {
            MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(dto.getSearchTerm())
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.AND);

            fields.forEach(queryBuilder::field);

            return queryBuilder;
        }

        return fields.stream()
                .findFirst()
                .map(field->QueryBuilders.matchQuery(field, dto.getSearchTerm())
                        .operator(Operator.AND))
                .orElse(null);
    }

    public static QueryBuilder getQueryBuilder(String search) {
        return QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", search))
                .should(QueryBuilders.matchQuery("content", search));
    }
    //-----------------------------------------------------<서치 요청>---------------------------------------------------------
    public static SearchRequest buildSearchRequest(String indexName, SearchRequestDTO dto) {
        try {
            /*int page = dto.getPage();
            int size = dto.getSize();
            int from = page <= 0 ? 0 : page * size;*/

            SearchSourceBuilder builder = new SearchSourceBuilder()
                    /*.from(page)
                    .size(size)*/
                    .postFilter(getQueryBuilder(dto));

            if (dto.getSortBy() != null) {
                builder = builder.sort(dto.getSortBy(), dto.getOrder() != null ? dto.getOrder() : SortOrder.ASC);
            }

            SearchRequest request = new SearchRequest(indexName);
            request.source(builder);

            return request;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SearchRequest buildSearchRequest(final String indexName, String search) {
        try {
            SearchSourceBuilder builder = new SearchSourceBuilder()
                    .postFilter(getQueryBuilder(search));

            SearchRequest request = new SearchRequest(indexName);
            request.source(builder);

            return request;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
