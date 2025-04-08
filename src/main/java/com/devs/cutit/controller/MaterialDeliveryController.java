package com.devs.cutit.controller;

import com.devs.cutit.DTO.CreateMaterialDeliveryDTO;
import com.devs.cutit.model.MaterialDeliveryModel;
import com.devs.cutit.service.MaterialDeliveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery-parts")
@Tag(name = "Delivery Parts")
public class MaterialDeliveryController {

    private MaterialDeliveryService materialDeliveryService;

    @Autowired
    public MaterialDeliveryController(MaterialDeliveryService materialDeliveryService) {
        this.materialDeliveryService = materialDeliveryService;
    }

    @PostMapping("/create")
    public MaterialDeliveryModel createRequestPart(@RequestBody CreateMaterialDeliveryDTO createMaterialDeliveryDTO) {
        return materialDeliveryService.registerDelivery(createMaterialDeliveryDTO);
    }
}

