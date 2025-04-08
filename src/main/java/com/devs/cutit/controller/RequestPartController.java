package com.devs.cutit.controller;

import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.service.RequestPartService;
import com.devs.cutit.DTO.CreateRequestPartDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/request-part")
@Tag(name = "Request Parts")
public class RequestPartController {

    private RequestPartService requestPartService;

    @Autowired
    public RequestPartController(RequestPartService requestPartService) {
        this.requestPartService = requestPartService;
    }

    @GetMapping("/search")
    public RequestPartModel searchRequestPart(@RequestParam UUID id) {
        return requestPartService.getRequestPart(id);
    }

    @PostMapping("/create")
    public RequestPartModel createRequestPart(@RequestBody CreateRequestPartDTO createRequestPartDTO) {
        return requestPartService.createRequestPart(createRequestPartDTO);
    }

    @GetMapping("/all")
    public List<RequestPartModel> searchAllRequestMaterial() {
        return requestPartService.getAllRequestPart();
    }
}
