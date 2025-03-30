package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateRequestMaterialDTO;
import com.devs.cutit.model.PartModel;
import com.devs.cutit.model.RequestMaterialModel;
import com.devs.cutit.repository.PartRepository;
import com.devs.cutit.repository.RequestMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestMaterialService {
    private final RequestMaterialRepository requestMaterialRepository;
    private final PartRepository partRepository;

    @Autowired
    public RequestMaterialService(RequestMaterialRepository requestMaterialRepository, PartRepository partRepository) {
        this.requestMaterialRepository = requestMaterialRepository;
        this.partRepository = partRepository;
    }

    public RequestMaterialModel createRequestMaterial(CreateRequestMaterialDTO createRequestMaterialDTO) {
        PartModel part = partRepository.findById(createRequestMaterialDTO.getPartId())
                .orElseThrow(() -> new RuntimeException("Part not found"));

        RequestMaterialModel requestMaterial = RequestMaterialModel.builder()
                .pieza(part)
                .quantity(createRequestMaterialDTO.getQuantity())
                .date(createRequestMaterialDTO.getDate())
                .status("PENDIENTE") // Estado predeterminado
                .build();

        return requestMaterialRepository.save(requestMaterial);
    }

    public RequestMaterialModel getRequestMaterial(UUID id) {
        return requestMaterialRepository.findById(id).orElse(null);
    }

    public List<RequestMaterialModel> getAllRequestMaterial() {
        return requestMaterialRepository.findAll();
    }
}
