package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.TemplateDtos; import com.tripwise.tripplanner.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/packing-templates") @RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @GetMapping
    public List<TemplateDtos.TemplateRes> list(){
        return templateService.listActive().stream().map(t ->
                new TemplateDtos.TemplateRes(
                        t.getId(), t.getName(), t.getDescription(), t.isActive(), t.getVersion(),
                        t.getItems().stream().map(i -> new TemplateDtos.ItemRes(i.getId(), i.getName(), i.getQtyPerPerson())).toList()
                )
        ).toList();
    }
}
