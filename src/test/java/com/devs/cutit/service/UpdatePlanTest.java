package com.devs.cutit.service;

import com.devs.cutit.DTO.UpdateStatusRequest;
import com.devs.cutit.model.PlanModel;
import com.devs.cutit.repository.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class UpdatePlanTest {

    private PlanService planService;
    @Mock
    private PlanRepository planRepositoryMock;

    @BeforeEach
    void setUp() {
        planService = new PlanService(planRepositoryMock);
    }

    @Test
    void When_UpdatePlan_Expect_UpdatePlan_In_BD() {

        UUID planID = UUID.randomUUID();
        PlanModel plan = PlanModel.builder()
                .id(planID)
                .name("Plan 1")
                .version(1)
                .description("Descripción")
                .attachment("Att")
                .note("No note")
                .status("PENDING")
                .build();

        given(planRepositoryMock.findById(planID)).willReturn(Optional.of(plan));

        UpdateStatusRequest planDTO = UpdateStatusRequest.builder()
                .id(planID)
                .status("APPROVED")
                .build();

        given(planRepositoryMock.save(plan)).willReturn(plan);

        planService.updateStatus(planDTO.id, planDTO.status);
    }
}
