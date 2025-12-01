package es.daw.hotelesapi.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}
