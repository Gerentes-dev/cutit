package com.devs.cutit.service;

import com.devs.cutit.model.PlanModel;
import com.devs.cutit.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public PlanModel createPlan(PlanModel plan) {
        return planRepository.save(plan);
    }

    public PlanModel getPlan(UUID id) {
        return planRepository.findById(id).orElse(null);
    }

    public List<PlanModel> getAllPlans() {
        return planRepository.findAll();
    }

    public List<Optional<PlanModel>>  getValidatedPlan(String status) {
        return planRepository.findBystatus(status);
    }

    public PlanModel updateStatus(UUID id, String status) {
        Optional<PlanModel> optionalPlan = planRepository.findById(id);
        
        if (optionalPlan.isPresent()) {
            PlanModel plan = optionalPlan.get();

            plan.setStatus(status);
            return planRepository.save(plan);
        }

        throw new EntityNotFoundException("Plano con ID: " + id + " no encontrado.");
    }
}