package com.devs.cutit;

import com.devs.cutit.DTO.CreateRequestPartDTO;
import com.devs.cutit.model.PartModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.repository.PartRepository;
import com.devs.cutit.repository.RequestPartRepository;
import com.devs.cutit.service.RequestPartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class RequestPartServiceTest {

    private RequestPartService requestPartService;
    @Mock
    private RequestPartRepository requestPartRepositoryMock;
    @Mock
    private PartRepository partRepositoryMock;

    @BeforeEach
    void setUp() {
        requestPartService = new RequestPartService(requestPartRepositoryMock, partRepositoryMock);
    }

    @Test
    void When_CreateRequestPart_Expect_CreateRequestPart_In_BD() {

        UUID partId = UUID.randomUUID();
        PartModel part = PartModel.builder()
                .id(partId)
                .name("cadena")
                .description("Cadena para sierras")
                .quantity(2)
                .build();

        given(partRepositoryMock.findById(partId)).willReturn(Optional.of(part));

        given(partRepositoryMock.save(part)).willReturn(part);

        /*PartModel part = RequestPartModel.findById(createRequestPartDTO.getPartId())
                .orElseThrow(() -> new RuntimeException("Part not found"));*/

        LocalDateTime date = LocalDateTime.now();
        CreateRequestPartDTO requestPartDTO = CreateRequestPartDTO.builder()
                .partId(partId)
                .quantity(2)
                .requestDate(date)
                .build();

        RequestPartModel requestPart = RequestPartModel.builder()
                .part(part)
                .quantity(2)
                .requestDate(date)
                .status("PENDING") // Estado predeterminado
                .build();

        given(requestPartRepositoryMock.save(requestPart)).willReturn(requestPart);

        requestPartService.createRequestPart(requestPartDTO);
    }
}
