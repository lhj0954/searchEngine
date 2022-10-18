package tnut.searchengine2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tnut.searchengine2.models.Dart;

import java.util.List;

public interface DartRepository extends ElasticsearchRepository<Dart, String> {

    @Query("{\"bool\":{\"should\":[{\"match\":{\"keyword\":{\"query\": \"?0\",\"operator\": \"and\"}}},{\"match\":{\"data.text\":{\"query\":\"?0\",\"operator\":\"and\"}}}]}}")
    Page<Dart> findByTitleMatchesAndContentMatches(String search, Pageable pageable);

    @Query("{\"match_all\": {}}")
    Page<Dart> findAllByIdExists(Pageable pageable);

}
