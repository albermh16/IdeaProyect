package es.daw.hotelesmvc.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Getter
@Setter
public class AuthResponseDTO {
    private String token;
}
