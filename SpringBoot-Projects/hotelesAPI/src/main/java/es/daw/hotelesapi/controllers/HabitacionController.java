package es.daw.hotelesapi.controllers;

import es.daw.hotelesapi.dto.HabitacionRequestDTO;
import es.daw.hotelesapi.dto.HabitacionResponseDTO;
import es.daw.hotelesapi.entity.Habitacion;
import es.daw.hotelesapi.service.HabitacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/habitaciones")
@Validated
public class HabitacionController {

    private final HabitacionService habitacionService;

    @GetMapping("{codigoHotel}/buscar")
    public ResponseEntity <List<HabitacionResponseDTO>> buscarHabitacion(@PathVariable String codigoHotel,
                                                                         @RequestParam(required = false) Integer tamanoMin,
                                                                         @RequestParam (required = false) BigDecimal precioMin,
                                                                         @RequestParam (required = false) BigDecimal precioMax) {

        return ResponseEntity.ok(habitacionService.buscarHabitacion(codigoHotel, tamanoMin, precioMin, precioMax));
    }

    @PostMapping("/{codigoHotel}")
    public ResponseEntity <HabitacionResponseDTO> crearHabitacion(@Valid @RequestBody HabitacionRequestDTO habitacionRequestDTO,
                                                                  @PathVariable String codigoHotel) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(habitacionService.crearHabitacion(habitacionRequestDTO, codigoHotel));
    }

    @DeleteMapping("/{codigoHabitacion}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable @Valid String codigoHabitacion) {
        habitacionService.borrar(codigoHabitacion);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{codigoHabitacion}/ocupar")
    public ResponseEntity<HabitacionResponseDTO> ocupar( @PathVariable String codigoHabitacion) {
        return ResponseEntity.ok(habitacionService.ocupar(codigoHabitacion));
    }
}
