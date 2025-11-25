package es.daw.comprasapi.controllers;

import es.daw.comprasapi.dto.ComprasDTO;
import es.daw.comprasapi.entity.Compras;
import es.daw.comprasapi.service.ComprasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
@Validated
public class ComprasController {
    private final ComprasService comprasService;

    @GetMapping
    public ResponseEntity<List<ComprasDTO>> findAll() {
        return ResponseEntity.status(200).body(comprasService.findAll());
    }

    @GetMapping ("/fecha/{fecha}")
    public ResponseEntity<List<ComprasDTO>> findByFecha(@PathVariable
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                        LocalDate fecha) {

        Optional<List<ComprasDTO>> comprasFilterByDate = comprasService.findByDate(fecha);

        if(comprasFilterByDate.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.status(200).body(comprasFilterByDate.get());

    }

    @PostMapping@PreAuthorize("isAuthenticated()")
    public ResponseEntity<ComprasDTO> create(@Valid @RequestBody ComprasDTO comprasDTO) {
        return comprasService.createCompra(comprasDTO)
                .map(creada -> ResponseEntity.status(HttpStatus.CREATED).body(creada))
                .orElse(ResponseEntity.notFound().build());

    }


}
