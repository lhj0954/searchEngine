package tnut.searchengine2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tnut.searchengine2.models.Dart;
import tnut.searchengine2.repositories.DartRepository;

import java.util.List;

@Service
public class DartService {

    public DartRepository dartRepository;

    @Autowired
    public DartService (DartRepository dartRepository) {
        this.dartRepository = dartRepository;
    }

    @Transactional
    public Page<Dart> findAllByIdExists(Pageable pageable) {
        return dartRepository.findAllByIdExists(pageable);
    }

    @Transactional
    public Page<Dart> searchOutcome(String search, Pageable pageable) {

        Page<Dart> darts;

        if (search.equals(" ")) {
            darts = dartRepository.findAllByIdExists(pageable);
        } else {
            darts = dartRepository.findByTitleMatchesAndContentMatches(search, pageable);
        }
        return darts;
    }

    /*@Transactional
    public Page<Dart> searchOutcome(String search, Pageable pageable) {
        return dartRepository.findByTitleMatchesAndContentMatches(search, pageable);
    }*/

    @Transactional
    public Dart findById(String id) {
        return dartRepository.findById(id).orElse(null);
    }
}
