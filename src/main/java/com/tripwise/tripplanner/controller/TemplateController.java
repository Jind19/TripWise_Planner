package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.TemplateDtos;
import com.tripwise.tripplanner.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/packing-templates")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @GetMapping
    public List<TemplateDtos.TemplateRes> list() {
        return templateService.list().stream()
                .map(t -> new TemplateDtos.TemplateRes(
                        t.getId(),
                        t.getName(),
                        t.getDescription(),
                        t.getItems().stream()
                                .map(i -> new TemplateDtos.ItemRes(i.getId(), i.getName(), i.getDefaultQuantity()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
