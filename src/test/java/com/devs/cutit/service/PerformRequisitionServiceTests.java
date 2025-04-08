package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateRequisitionDTO;
import com.devs.cutit.model.ChainsawModel;
import com.devs.cutit.model.PerformRequisitionModel;
import com.devs.cutit.repository.ChainsawRepository;
import com.devs.cutit.repository.PerformRequisitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class PerformRequisitionServiceTests{

    private PerformRequisitionService performRequisitionService;
    @Mock
    private PerformRequisitionRepository performRequisitionRepositorymock;
    @Mock
    private ChainsawRepository chainsawRepositorymock;

    @BeforeEach
    void setUp() {
        performRequisitionService = new PerformRequisitionService(performRequisitionRepositorymock, chainsawRepositorymock);
    }
    @Test
    void When_CreatePerformRequisition_Expect_CreatePerformRequisition_In_BD() {

        UUID chainsawId = UUID.randomUUID();
       ChainsawModel chainsaw = ChainsawModel.builder()
                .id(chainsawId)
                .name("sierras")
                .description("Cadena para las requisiciones")
                .quantity(2)
                .build();

        given(chainsawRepositorymock.findById(chainsawId)).willReturn(Optional.of(chainsaw));

        given(chainsawRepositorymock.save(chainsaw)).willReturn(chainsaw);

        LocalDateTime date = LocalDateTime.now();
       CreateRequisitionDTO requisitionDTO = CreateRequisitionDTO.builder()
                .chainsawId(chainsawId)
                .quantity(2)
                .requisitionDate(date)
                .observation("")
                .build();

        PerformRequisitionModel performRequisition = PerformRequisitionModel.builder()
                .chainsawModel(chainsaw)
                .quantity(2)
                .requisitionDate(date)
                .observation("")
                .status("PENDING") // Estado predeterminado
                .build();

        given(performRequisitionRepositorymock.save(performRequisition)).willReturn(performRequisition);

        performRequisitionService.createPerformRequisition(requisitionDTO);
    }
}