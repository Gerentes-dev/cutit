package com.devs.cutit.DTO;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    private String username;
    private String password;
    private String status;
}
