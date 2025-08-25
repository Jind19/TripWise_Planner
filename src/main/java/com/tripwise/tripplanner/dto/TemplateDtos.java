// dto/TemplateDtos.java
package com.tripwise.tripplanner.dto;

import java.util.List;
import java.util.UUID;

public class TemplateDtos {
    public record TemplateRes(UUID id, String name, String description, List<ItemRes> items) {}
    public record ItemRes(UUID id, String name, int defaultQuantity) {}
}
