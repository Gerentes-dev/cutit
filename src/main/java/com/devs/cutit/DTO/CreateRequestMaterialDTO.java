package com.devs.cutit.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestMaterialDTO {
    private UUID partId;
    private Integer quantity;
    private LocalDateTime date;
}
