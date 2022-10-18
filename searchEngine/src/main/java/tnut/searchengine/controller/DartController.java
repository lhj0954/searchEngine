package tnut.searchengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tnut.searchengine.models.Dart;
import tnut.searchengine.service.DartService;

import java.util.List;


@RestController
public class DartController {

    private final DartService dartService;

    @Autowired
    public DartController(DartService dartService) {
        this.dartService = dartService;
    }

    @GetMapping("/dart/{search}")
    public List<Dart> dartSearch( @PathVariable String search) {
        return dartService.dartSearch(search);
    }
}
