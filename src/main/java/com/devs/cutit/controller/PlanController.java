package com.devs.cutit.controller;

import com.devs.cutit.DTO.UpdateStatusRequest;
import com.devs.cutit.model.PlanModel;
import com.devs.cutit.service.PlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/plans")
@Tag(name = "Plan Controller")
public class PlanController {

    private PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/search")
    public PlanModel searchPlan(@RequestParam UUID id) {
        return planService.getPlan(id);
    }

    @GetMapping("/all")
    public List<PlanModel> searchAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/search_validated")
    public List<Optional<PlanModel>> searchValidated(@RequestParam String status) {
        return planService.getValidatedPlan(status);
    }

    @PostMapping("/create")
    public PlanModel createPlan(@RequestBody PlanModel planModel) {
        return planService.createPlan(planModel);
    }

    @PutMapping("/edit")
    public PlanModel editPlan(@RequestBody PlanModel planModel) {
        return planService.createPlan(planModel);
    }

    @PutMapping("/updateStatus")
    public PlanModel updateStatus(@RequestBody UpdateStatusRequest request) {
        return planService.updateStatus(request.id, request.status);
    }
}