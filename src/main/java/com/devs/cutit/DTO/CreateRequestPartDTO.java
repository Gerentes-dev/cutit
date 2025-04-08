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
public class CreateRequestPartDTO {
    private UUID partId;
    private Integer quantity;
    private LocalDateTime requestDate;
}
