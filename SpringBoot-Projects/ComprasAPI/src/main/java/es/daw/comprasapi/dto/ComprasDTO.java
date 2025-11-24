package es.daw.comprasapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprasDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombnre es obligatorio")
    private String cliente;

    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm")
    private LocalDateTime fecha;

    @DecimalMin(value= "300",message = "el precio minimo son 800")
    private double total;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> productosId;


}
