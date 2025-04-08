package com.devs.cutit.controller;

import com.devs.cutit.DTO.ChainsawDto;
import com.devs.cutit.model.ChainsawModel;
import com.devs.cutit.service.ChainsawService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chainsaws")
@Tag(name = "Chainsaws controller")
public class ChainsawController {

    private ChainsawService chainsawService;

    @Autowired
    public ChainsawController(ChainsawService chainsawService) {
        this.chainsawService = chainsawService;
    }

    @GetMapping("/search")
    public ChainsawModel searchPart(@RequestParam UUID id) {
        return chainsawService.getChainsaw(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ChainsawDto> createChainsaw(@RequestBody ChainsawDto chainsawDto) {
        ChainsawDto createdChainsaw = chainsawService.createChainsaw(chainsawDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChainsaw);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ChainsawDto> updateChainsaw(@PathVariable UUID id, @RequestBody ChainsawDto chainsawDto) {
        ChainsawDto updatedChainsaw = chainsawService.updateChainsaw(id, chainsawDto);
        return ResponseEntity.ok(updatedChainsaw);
    }

    @GetMapping("/all")
    public List<ChainsawModel> searchAllOrders() {
        return chainsawService.getAllChainsaws();
    }
}
