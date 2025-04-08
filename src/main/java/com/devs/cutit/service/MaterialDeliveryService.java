package com.devs.cutit.service;


import com.devs.cutit.DTO.CreateMaterialDeliveryDTO;
import com.devs.cutit.model.MaterialDeliveryModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.model.PartModel;
import com.devs.cutit.repository.MaterialDeliveryRepository;
import com.devs.cutit.repository.PartRepository;
import com.devs.cutit.repository.RequestPartRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialDeliveryService {

    private final MaterialDeliveryRepository materialDeliveryRepository;
    private final RequestPartRepository requestPartRepository;
    private final PartRepository partRepository;

    @Autowired
    public MaterialDeliveryService(MaterialDeliveryRepository materialDeliveryRepository,
                                   RequestPartRepository requestPartRepository,
                                   PartRepository partRepository) {
        this.materialDeliveryRepository = materialDeliveryRepository;
        this.requestPartRepository = requestPartRepository;
        this.partRepository = partRepository;
    }

    public MaterialDeliveryModel registerDelivery(CreateMaterialDeliveryDTO dto) {
        if (dto.getRequestId() == null || dto.getQuantityDelivered() == null || dto.getDeliveryDate() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        RequestPartModel request = requestPartRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada"));

        if (dto.getQuantityDelivered() >= request.getQuantity()) {
            request.setStatus("Delivered");
        } else {
            request.setStatus("Partially Delivered");
        }

        requestPartRepository.save(request);

        PartModel part = partRepository.findById(request.getPart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Parte no encontrada"));

        part.setQuantity(part.getQuantity() + dto.getQuantityDelivered());
        partRepository.save(part);

        MaterialDeliveryModel delivery = MaterialDeliveryModel.builder()
                .requestParts(request)
                .quantityDelivered(dto.getQuantityDelivered())
                .deliveryDate(dto.getDeliveryDate())
                .build();

        return materialDeliveryRepository.save(delivery);
    }
}

