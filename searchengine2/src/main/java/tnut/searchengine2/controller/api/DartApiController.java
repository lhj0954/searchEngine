package tnut.searchengine2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tnut.searchengine2.models.Dart;
import tnut.searchengine2.service.DartService;

@RestController
public class DartApiController {

    private final DartService dartService;

    @Autowired
    public DartApiController(DartService dartService) {
        this.dartService = dartService;
    }

    @GetMapping("/getSource/test/{search}")
    public Page<Dart> getSource (@PathVariable String search, Pageable pageable) {
        return dartService.searchOutcome(search, pageable);
    }
}
