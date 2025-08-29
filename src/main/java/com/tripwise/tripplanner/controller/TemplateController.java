package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.TemplateDtos;
import com.tripwise.tripplanner.model.PackingTemplate;
import com.tripwise.tripplanner.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/packing-templates")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @GetMapping
    public List<TemplateDtos.TemplateRes> list() {
        return templateService.listActive().stream().map(this::toRes).toList();
    }


    @PostMapping
    public TemplateDtos.TemplateRes create(@Valid @RequestBody TemplateDtos.CreateReq req) {
        PackingTemplate t = templateService.create(req);
        return toRes(t);
    }


    @PutMapping("/{id}")
    public TemplateDtos.TemplateRes update(@PathVariable Long id,
                                           @Valid @RequestBody TemplateDtos.UpdateReq req) {
        PackingTemplate t = templateService.update(id, req);
        return toRes(t);
    }

    private TemplateDtos.TemplateRes toRes(PackingTemplate t) {
        return new TemplateDtos.TemplateRes(
                t.getId(), t.getName(), t.getDescription(), t.isActive(), t.getVersion(),
                t.getItems().stream()
                        .map(i -> new TemplateDtos.ItemRes(i.getId(), i.getName(), i.getQtyPerPerson()))
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        templateService.delete(id);
    }
}
