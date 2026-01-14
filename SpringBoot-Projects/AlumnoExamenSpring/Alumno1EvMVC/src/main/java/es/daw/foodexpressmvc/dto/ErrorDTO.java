package es.daw.foodexpressmvc.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
