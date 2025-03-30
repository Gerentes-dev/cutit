package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateRequestPartDTO;
import com.devs.cutit.model.PartModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.repository.PartRepository;
import com.devs.cutit.repository.RequestPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestPartService {
    private final RequestPartRepository requestPartRepository;
    private final PartRepository partRepository;

    @Autowired
    public RequestPartService(RequestPartRepository requestPartRepository, PartRepository partRepository) {
        this.requestPartRepository = requestPartRepository;
        this.partRepository = partRepository;
    }

    public RequestPartModel createRequestPart(CreateRequestPartDTO createRequestPartDTO) {
        PartModel part = partRepository.findById(createRequestPartDTO.getPartId())
                .orElseThrow(() -> new RuntimeException("Part not found"));

        RequestPartModel requestPart = RequestPartModel.builder()
                .part(part)
                .quantity(createRequestPartDTO.getQuantity())
                .requestDate(createRequestPartDTO.getRequestDate())
                    .status("PENDING") // Estado predeterminado
                .build();

        return requestPartRepository.save(requestPart);
    }

    public RequestPartModel getRequestPart(UUID id) {
        return requestPartRepository.findById(id).orElse(null);
    }

    public List<RequestPartModel> getAllRequestPart() {
        return requestPartRepository.findAll();
    }
}
