package com.devs.cutit.service;

import com.devs.cutit.DTO.CreateMaterialDeliveryDTO;
import com.devs.cutit.model.MaterialDeliveryModel;
import com.devs.cutit.model.PartModel;
import com.devs.cutit.model.RequestPartModel;
import com.devs.cutit.repository.MaterialDeliveryRepository;
import com.devs.cutit.repository.PartRepository;
import com.devs.cutit.repository.RequestPartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class MaterialDeliveryServiceTes {

    @Mock
    private MaterialDeliveryRepository deliveryRepository;

    @Mock
    private RequestPartRepository requestPartRepository;

    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private MaterialDeliveryService deliveryService;

    private UUID requestId;
    private UUID partId;
    private RequestPartModel request;
    private PartModel part;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestId = UUID.randomUUID();
        partId = UUID.randomUUID();

        part = new PartModel();
        part.setId(partId);
        part.setQuantity(10);

        request = new RequestPartModel();
        request.setId(requestId);
        request.setQuantity(5);
        request.setStatus("PENDING");
        request.setPart(part);
    }

    @Test
    void testFullDelivery_ChangesStatusAndUpdatesInventory() {
        CreateMaterialDeliveryDTO dto = CreateMaterialDeliveryDTO.builder()
                .requestId(requestId)
                .quantityDelivered(5)
                .deliveryDate(LocalDateTime.now())
                .build();

        when(requestPartRepository.findById(requestId)).thenReturn(Optional.of(request));
        when(partRepository.findById(partId)).thenReturn(Optional.of(part));
        when(deliveryRepository.save(any(MaterialDeliveryModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MaterialDeliveryModel result = deliveryService.registerDelivery(dto);

        assertEquals("Delivered", request.getStatus());
        assertEquals(15, part.getQuantity());
        assertEquals(request, result.getRequestParts());

        verify(requestPartRepository).save(request);
        verify(partRepository).save(part);
        verify(deliveryRepository).save(any());
    }

    @Test
    void testPartialDelivery_SetsCorrectStatus() {
        // Arrange
        CreateMaterialDeliveryDTO dto = CreateMaterialDeliveryDTO.builder()
                .requestId(requestId)
                .quantityDelivered(3)
                .deliveryDate(LocalDateTime.now())
                .build();

        when(requestPartRepository.findById(requestId)).thenReturn(Optional.of(request));
        when(partRepository.findById(partId)).thenReturn(Optional.of(part));
        when(deliveryRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        deliveryService.registerDelivery(dto);

        assertEquals("Partially Delivered", request.getStatus());
        assertEquals(13, part.getQuantity());

        verify(requestPartRepository).save(request);
        verify(partRepository).save(part);
    }

    @Test
    void testMissingRequiredFields_ThrowsException() {
        CreateMaterialDeliveryDTO dto = new CreateMaterialDeliveryDTO();

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            deliveryService.registerDelivery(dto);
        });

        assertEquals("Todos los campos son obligatorios.", ex.getMessage());
        verifyNoInteractions(requestPartRepository, partRepository, deliveryRepository);
    }
}
