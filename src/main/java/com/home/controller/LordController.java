package com.home.controller;

import com.home.dto.LordRequest;
import com.home.dto.LordResponse;
import com.home.model.Lord;
import com.home.service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/lords")
public class LordController {

    private final LordService lordService;

    @Autowired
    public LordController(LordService lordService) {
        this.lordService = lordService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public LordResponse createLord(@Valid @RequestBody LordRequest request) {
        Lord lord = lordService.createLord(request.getName(), request.getAge());

        return new LordResponse(lord.getId(), lord.getName(), lord.getAge());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LordResponse> getLords(@RequestParam(value = "topYoungest", required = false) Integer topYoungest,
                                       @RequestParam(value = "planetsCount", required = false) Integer planetsCount) {
        return lordService.getLords(topYoungest, planetsCount).stream()
                .map(lord -> new LordResponse(lord.getId(), lord.getName(), lord.getAge()))
                .collect(Collectors.toList());
    }
}

