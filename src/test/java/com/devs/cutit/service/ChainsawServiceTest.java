package com.devs.cutit.service;

import com.devs.cutit.DTO.ChainsawDto;
import com.devs.cutit.DTO.CreateRequestPartDTO;
import com.devs.cutit.model.ChainsawModel;
import com.devs.cutit.model.PlanModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.repository.ChainsawRepository;
import com.devs.cutit.repository.PlanRepository;
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
public class ChainsawServiceTest {
    @Mock
    private ChainsawRepository chainsawRepositoryMock;
    @Mock
    private PlanRepository planRepositoryMock;

    private ChainsawService chainsawService;


    @BeforeEach
    void setUp() {
        chainsawService = new ChainsawService(chainsawRepositoryMock, planRepositoryMock);
    }

    @Test
    void When_CreateRequestPart_Expect_CreateRequestPart_In_BD() {

        UUID partId = UUID.randomUUID();
        PlanModel plan = PlanModel.builder()
                .id(partId)
                .name("cadena")
                .description("Cadena para sierras")
                .build();

        given(planRepositoryMock.findById(partId)).willReturn(Optional.ofNullable(plan));

        LocalDateTime date = LocalDateTime.now();
        ChainsawDto requestPartDTO = ChainsawDto.builder()
                .planId(partId)
                .quantity(2)
                .description("test")
                .type("test")
                .build();

        ChainsawModel chainsawModel = ChainsawModel.builder()
                .plan(plan)
                .quantity(2)
                .description("test")
                .type("test")
                .build();

        given(chainsawRepositoryMock.save(chainsawModel)).willReturn(chainsawModel);

        chainsawService.createChainsaw(requestPartDTO);
    }
}
