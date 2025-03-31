package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateMaterialDeliveryDTO;
import com.devs.cutit.model.MaterialDeliveryModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.repository.MaterialDeliveryRepository;
import com.devs.cutit.repository.RequestPartRepository;

public class MaterialDeliveryService {

    private final MaterialDeliveryRepository materialDeliveryRepository;
    private  final RequestPartRepository requestPartRepository;

    public MaterialDeliveryService(MaterialDeliveryRepository materialDeliveryRepository, RequestPartRepository requestPartRepository) {
        this.materialDeliveryRepository = materialDeliveryRepository;
        this.requestPartRepository = requestPartRepository;
    }

    public MaterialDeliveryModel registerDelivery(CreateMaterialDeliveryDTO createMaterialDeliveryDTO) {

        RequestPartModel requestPartModel = requestPartRepository.findById(createMaterialDeliveryDTO.getRequestId())
                .orElseThrow(() -> new RuntimeException("Part not found"));
        Double dblCantidadActual = materialDeliveryRepository.obtenerSumaPorRegistro(createMaterialDeliveryDTO.getRequestId());

        if (requestPartModel.getQuantity() <= 0 || requestPartModel.getRequestDate() == null) {
            throw new IllegalArgumentException("Missing or invalid required fields.");
        }

        MaterialDeliveryModel delivery = new MaterialDeliveryModel();
        delivery.setRequestParts(requestPartModel) ;
        delivery.setQuantityDelivered(createMaterialDeliveryDTO.getQuantityDelivered());
        delivery.setDeliveryDate(createMaterialDeliveryDTO.getDeliveryDate());

        if (requestPartModel.getQuantity() >= (dblCantidadActual + createMaterialDeliveryDTO.getQuantityDelivered() )) {
            requestPartRepository.updateStatus(createMaterialDeliveryDTO.getRequestId(),"Delivered");
        } else {
            requestPartRepository.updateStatus(createMaterialDeliveryDTO.getRequestId(),"Partially Delivered");
        }

        return materialDeliveryRepository.save(delivery);
    }
}
