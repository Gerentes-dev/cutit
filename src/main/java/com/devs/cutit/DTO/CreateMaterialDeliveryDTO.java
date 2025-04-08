package com.devs.cutit.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialDeliveryDTO {
    private UUID requestId;
    private Integer quantityDelivered;
    private LocalDateTime deliveryDate;
}
