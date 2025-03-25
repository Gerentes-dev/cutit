package com.devs.cutit.service;

import com.devs.cutit.model.OrderModel;
import com.devs.cutit.model.PerformRequisitionModel;
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

    @Autowired
    public PerformRequisitionService(PerformRequisitionRepository performRequisitionRepository) {
        this.performRequisitionRepository = performRequisitionRepository;
    }

    public PerformRequisitionModel createPerformRequisition(PerformRequisitionModel performRequisitionModel) {
        return performRequisitionRepository.save(performRequisitionModel);
    }


    public PerformRequisitionModel getPerformRequisition(UUID id) {
        return performRequisitionRepository.findById(id).orElse(null);
    }

    public List<PerformRequisitionModel> getAllPerformRequisition() {
        return performRequisitionRepository.findAll();
    }

}

