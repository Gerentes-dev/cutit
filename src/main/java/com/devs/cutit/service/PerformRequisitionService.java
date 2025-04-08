package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateRequisitionDTO;
import com.devs.cutit.model.*;
import com.devs.cutit.repository.ChainsawRepository;
import com.devs.cutit.repository.OrderRepository;
import com.devs.cutit.repository.PerformRequisitionRepository;
import com.devs.cutit.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PerformRequisitionService {
    private final PerformRequisitionRepository performRequisitionRepository;
    private final ChainsawRepository chainsawRepository;

    @Autowired
    public PerformRequisitionService(PerformRequisitionRepository performRequisitionRepository,ChainsawRepository chainsawRepository) {
        this.performRequisitionRepository = performRequisitionRepository;
        this.chainsawRepository = chainsawRepository;
    }

    public PerformRequisitionModel createPerformRequisition(CreateRequisitionDTO createRequisitionDTO) {
        ChainsawModel chainsaw = chainsawRepository.findById(createRequisitionDTO.getChainsawId())
                .orElseThrow(() -> new RuntimeException("Chainsaw not found"));

        PerformRequisitionModel requisition = PerformRequisitionModel.builder()
                .chainsawModel(chainsaw)
                .quantity(createRequisitionDTO.getQuantity())
                .requisitionDate(createRequisitionDTO.getRequisitionDate())
                .observation(createRequisitionDTO.getObservation())
                .status("PENDING") // Estado predeterminado
                .build();
        return performRequisitionRepository.save(requisition);
    }


    public PerformRequisitionModel getPerformRequisition(UUID id) {
        return performRequisitionRepository.findById(id).orElse(null);
    }

    public List<PerformRequisitionModel> getAllPerformRequisition() {
        return performRequisitionRepository.findAll();
    }

}

