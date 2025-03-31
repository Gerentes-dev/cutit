package com.devs.cutit.controller;

import com.devs.cutit.DTO.CreateRequisitionDTO;
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
    public PerformRequisitionModel searchRequisition(@RequestParam UUID id) {
        return performRequisitionService.getPerformRequisition(id);
    }

    @PostMapping("/create")
    public PerformRequisitionModel createRequisition(@RequestBody CreateRequisitionDTO createRequisitionDTO) {
        return performRequisitionService.createPerformRequisition(createRequisitionDTO);
    }

    @GetMapping("/all")
    public List<PerformRequisitionModel> searchAllPerformRequisition() {
        return performRequisitionService.getAllPerformRequisition();
    }
}
