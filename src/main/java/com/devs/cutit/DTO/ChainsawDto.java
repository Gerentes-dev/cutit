package com.devs.cutit.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChainsawDto {
    private UUID id;
    private String name;
    private String description;
    private Integer quantity;
    private String type;
    private UUID planId;
}
