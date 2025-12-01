package es.daw.hotelesapi.controllers;

import es.daw.hotelesapi.dto.HotelRequestDTO;
import es.daw.hotelesapi.dto.HotelResponseDTO;
import es.daw.hotelesapi.repository.HotelRepository;
import es.daw.hotelesapi.service.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hoteles")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/buscar")
    public ResponseEntity<List<HotelResponseDTO>> buscarHoteles(@RequestParam(required = false) String localidad,
                                                            @RequestParam(required = false) String codigoCategoria){
        return ResponseEntity.ok(hotelService.buscarHoteles(localidad, codigoCategoria));

    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@Valid @RequestBody HotelRequestDTO hotelRequestDTO){
        return ResponseEntity.ok(hotelService.crearHotel(hotelRequestDTO));
    }
}
