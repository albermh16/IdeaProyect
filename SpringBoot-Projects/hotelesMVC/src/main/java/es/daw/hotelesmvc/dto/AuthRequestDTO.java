package es.daw.hotelesmvc.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
}
