package com.devs.cutit.service;

import com.devs.cutit.model.PartModel;
import com.devs.cutit.repository.PartRepository;
import com.devs.cutit.repository.PlanPieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PartService {
    private final PartRepository partRepository;
    private final PlanPieceRepository planPieceRepository;

    @Autowired
    public PartService(PartRepository partRepository, PlanPieceRepository planPieceRepository) {
        this.partRepository = partRepository;
        this.planPieceRepository = planPieceRepository;
    }

    public PartModel createPart(PartModel plan) {
        return partRepository.save(plan);
    }

    public PartModel getPart(UUID id) {
        return partRepository.findById(id).orElse(null);
    }

    public List<PartModel> getAllParts() {
        return partRepository.findAll();
    }

    public List<PartModel> getAllPartsByPlan(UUID id) {
        return planPieceRepository.findPartsByPlanoId(id);
    }
}