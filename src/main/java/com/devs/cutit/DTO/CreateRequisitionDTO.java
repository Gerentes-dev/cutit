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
public class CreateRequisitionDTO {
    private UUID chainsawId;
    private Integer quantity;
    private LocalDateTime requisitionDate;
    private String observation;
}