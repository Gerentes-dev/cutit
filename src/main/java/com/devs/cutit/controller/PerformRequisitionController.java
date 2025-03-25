package com.devs.cutit.controller;

import com.devs.cutit.model.PerformRequisitionModel;
import com.devs.cutit.service.PerformRequisitionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/performRequisition")
@Tag(name = "DeparturePerformRequisition", description = "API for managing Perform Requisitions")
public class PerformRequisitionController {

    private final PerformRequisitionService performRequisitionService;
    @Autowired
    public PerformRequisitionController(PerformRequisitionService performRequisitionService) {
        this.performRequisitionService = performRequisitionService;
    }

    @GetMapping("/search")
    public PerformRequisitionModel searchPart(@RequestParam UUID id) {
        return performRequisitionService.getPerformRequisition(id);
    }

    @PostMapping("/create")
    public PerformRequisitionModel createPart(@RequestBody PerformRequisitionModel performRequisitionModel) {
        return performRequisitionService.createPerformRequisition(performRequisitionModel);
    }

    @PutMapping("/edit")
    public PerformRequisitionModel editPart(@RequestBody PerformRequisitionModel performRequisitionModel) {
        return performRequisitionService.createPerformRequisition(performRequisitionModel);
    }

    @GetMapping("/all")
    public List<PerformRequisitionModel> searchAllPerformRequisition() {
        return performRequisitionService.getAllPerformRequisition();
    }
}
