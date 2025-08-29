package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.dto.TemplateDtos;
import com.tripwise.tripplanner.model.*;
import com.tripwise.tripplanner.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList; import java.util.List;

@Service @RequiredArgsConstructor
public class TemplateService {
    private final PackingTemplateRepository templates;
    private final PackingTemplateItemRepository items;


    public List<PackingTemplate> listActive(){ return templates.findByActiveTrueOrderByNameAsc(); }


    @Transactional
    public PackingTemplate create(TemplateDtos.CreateReq req){
        PackingTemplate t = templates.save(PackingTemplate.builder()
                .name(req.name()).description(req.description()).active(true).version(1).build());
        if (req.items() != null) {
            List<PackingTemplateItem> add = new ArrayList<>();
            for (TemplateDtos.ItemReq i : req.items()) {
                add.add(items.save(PackingTemplateItem.builder().template(t).name(i.name()).qtyPerPerson(i.qtyPerPerson()).notes(i.notes()).build()));
            }
            t.setItems(add);
        }
        return t;
    }


    @Transactional
    public PackingTemplate update(Long id, TemplateDtos.UpdateReq req){
        PackingTemplate t = templates.findById(id).orElseThrow();
        t.setName(req.name());
        t.setDescription(req.description());
        if (req.active() != null) t.setActive(req.active());

        // clear existing children safely
        t.getItems().clear();

        // add new children into the *same collection instance*
        if (req.items() != null) {
            for (TemplateDtos.ItemReq i : req.items()) {
                t.getItems().add(
                        PackingTemplateItem.builder()
                                .template(t)
                                .name(i.name())
                                .qtyPerPerson(i.qtyPerPerson())
                                .notes(i.notes())
                                .build()
                );
            }
        }

        t.setVersion(t.getVersion() + 1);
        return templates.save(t);
    }

    @Transactional
    public void delete(Long id) {
        templates.deleteById(id);
    }
}
