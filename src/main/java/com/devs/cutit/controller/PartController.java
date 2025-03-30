package com.devs.cutit.controller;

import com.devs.cutit.model.PartModel;
import com.devs.cutit.service.PartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/* :) */

@RestController
@RequestMapping("/parts")
@Tag(name = "Part Controller")
public class PartController {

    private PartService partService;

    public PartController (PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/search")
    public PartModel searchPart(@RequestParam UUID id) {
        return partService.getPart(id);
    }

    @PostMapping("/create")
    public PartModel createPart(@RequestBody PartModel partModel) {
        return partService.createPart(partModel);
    }

    @PutMapping("/edit")
    public PartModel editPart(@RequestBody PartModel partModel) {
        return partService.createPart(partModel);
    }

    @GetMapping("/all")
    public List<PartModel> searchAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/all_by_plan")
    public List<PartModel> searchAllParts(UUID id) {
        return partService.getAllPartsByPlan(id);
    }
}