package com.devs.cutit.service;

import com.devs.cutit.DTO.ChainsawDto;
import com.devs.cutit.model.ChainsawModel;
import com.devs.cutit.model.PlanModel;
import com.devs.cutit.repository.ChainsawRepository;
import com.devs.cutit.repository.PlanRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChainsawService {
    private final ChainsawRepository chainsawRepository;
    private final PlanRepository planRepository;

    @Autowired
    public ChainsawService(ChainsawRepository chainsawRepository, PlanRepository planRepository) {
        this.chainsawRepository = chainsawRepository;
        this.planRepository = planRepository;
    }

    public ChainsawModel getChainsaw(UUID id) {
        return chainsawRepository.findById(id).orElse(null);
    }

    public List<ChainsawModel> getAllChainsaws() {
        return chainsawRepository.findAll();
    }

    public ChainsawDto createChainsaw(ChainsawDto chainsawDto) {
        ChainsawModel entity = convertToEntity(chainsawDto);
        ChainsawModel savedEntity = chainsawRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public ChainsawDto updateChainsaw(UUID id, ChainsawDto chainsawDto) {
        ChainsawModel existingEntity = chainsawRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Chainsaw not found"));

        existingEntity.setName(chainsawDto.getName());
        existingEntity.setDescription(chainsawDto.getDescription());
        existingEntity.setQuantity(chainsawDto.getQuantity());
        existingEntity.setType(chainsawDto.getType());

        ChainsawModel updatedEntity = chainsawRepository.save(existingEntity);
        return convertToDto(updatedEntity);
    }

    public ChainsawDto convertToDto(ChainsawModel entity) {
        ChainsawDto dto = new ChainsawDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setQuantity(entity.getQuantity());
        dto.setType(entity.getType());
        return dto;
    }

    public ChainsawModel convertToEntity(ChainsawDto dto) {
        ChainsawModel entity = new ChainsawModel();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setQuantity(dto.getQuantity());
        entity.setType(dto.getType());

        UUID planId = dto.getPlanId();
        if (planId != null) {
            PlanModel plan = planRepository.findById(planId)
                    .orElseThrow(() -> new OpenApiResourceNotFoundException("Plan not found with id: " + planId));
            entity.setPlan(plan);
        }

        return entity;
    }
}