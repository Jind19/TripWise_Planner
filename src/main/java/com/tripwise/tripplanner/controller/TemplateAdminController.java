package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.TemplateDtos; import com.tripwise.tripplanner.model.PackingTemplate;
import com.tripwise.tripplanner.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/admin/packing-templates") @RequiredArgsConstructor
public class TemplateAdminController {
    private final TemplateService templates;

    @PostMapping @PreAuthorize("hasRole('ADMIN')")
    public TemplateDtos.TemplateRes create(@Valid @RequestBody TemplateDtos.CreateReq req){
        PackingTemplate t = templates.create(req);
        return toRes(t);
    }

    @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')")
    public TemplateDtos.TemplateRes update(@PathVariable Long id, @Valid @RequestBody TemplateDtos.UpdateReq req){
        PackingTemplate t = templates.update(id, req);
        return toRes(t);
    }

    private static TemplateDtos.TemplateRes toRes(PackingTemplate t){
        return new TemplateDtos.TemplateRes(
                t.getId(), t.getName(), t.getDescription(), t.isActive(), t.getVersion(),
                t.getItems().stream().map(i -> new TemplateDtos.ItemRes(i.getId(), i.getName(), i.getQtyPerPerson())).toList()
        );
    }
}
