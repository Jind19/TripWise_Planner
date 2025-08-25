package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.model.PackingTemplate;
import com.tripwise.tripplanner.repo.PackingTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final PackingTemplateRepository templates;

    @Transactional(readOnly = true)
    public List<PackingTemplate> list() {
        return templates.findAll();
    }
}

