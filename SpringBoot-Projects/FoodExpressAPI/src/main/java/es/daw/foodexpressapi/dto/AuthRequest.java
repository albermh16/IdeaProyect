package es.daw.foodexpressapi.dto;

import lombok.Data;

@Data
public class AuthRequest {
    // Pendiente validaciones...
    private String password;
    private String username;

}
