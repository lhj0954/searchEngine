package tnut.searchengine2.controller;

import org.elasticsearch.client.license.LicensesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tnut.searchengine2.models.Dart;
import tnut.searchengine2.service.DartService;

import java.util.List;
import java.util.Objects;

@Controller
public class DartController {

    private final DartService dartService;

    @Autowired
    public DartController(DartService dartService) {
        this.dartService = dartService;
    }

    @GetMapping("/searchform/dartSearchForm")
    public String dartSearchForm(Model model,
                                 Pageable pageable,
                                 @RequestParam(required = false, defaultValue = " ") String search) {

        Page<Dart> darts = dartService.searchOutcome(search, pageable);

        int startPage = Math.max(1, darts.getPageable().getPageNumber() - 3);
        int endPage = Math.min(darts.getTotalPages(), darts.getPageable().getPageNumber() + 3);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("darts", darts);

        return "searchForm/dartSearchForm";
    }

    @GetMapping("/contentform/dartContentForm/{id}")
    public String dartContentForm(Model model, @PathVariable String id) {

        model.addAttribute("dart", dartService.findById(id));

        return "contentform/dartContentForm";
    }
}
