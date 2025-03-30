package com.devs.cutit.controller;

import com.devs.cutit.model.RequestMaterialModel;
import com.devs.cutit.service.RequestMaterialService;
import com.devs.cutit.DTO.CreateRequestMaterialDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/request-material")
@Tag(name = "Request Materials")
public class RequestMaterialController {

    private RequestMaterialService requestMaterialService;

    @Autowired
    public RequestMaterialController (RequestMaterialService requestMaterialService) {
        this.requestMaterialService = requestMaterialService;
    }

    @GetMapping("/search")
    public RequestMaterialModel searchRequestMaterial(@RequestParam UUID id) {
        return requestMaterialService.getRequestMaterial(id);
    }

    @PostMapping("/create")
    public RequestMaterialModel createRequestMaterial(@RequestBody CreateRequestMaterialDTO createRequestMaterialDTO) {
        return requestMaterialService.createRequestMaterial(createRequestMaterialDTO);
    }

    @GetMapping("/all")
    public List<RequestMaterialModel> searchAllRequestMaterial() {
        return requestMaterialService.getAllRequestMaterial();
    }
}
