package es.daw.practicaproductosapi.controller;

import es.daw.practicaproductosapi.dto.ProductoDTO;
import es.daw.practicaproductosapi.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Validated
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping ("/{codigo}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable String codigo) {
        Optional<ProductoDTO> dto = productoService.findByCodigo(codigo);
        if (dto.isPresent()) {
            return ResponseEntity.ok(dto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody @Valid ProductoDTO productoDTO) {
        Optional<ProductoDTO> dto =productoService.create(productoDTO);
        if(dto.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dto.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping ("/{codigo}")
    public ResponseEntity<ProductoDTO> update (@PathVariable String codigo, @RequestBody @Valid ProductoDTO productoDTO) {
        Optional<ProductoDTO> dto = productoService.update(codigo, productoDTO);

        if(dto.isPresent()) {
            return ResponseEntity.ok(dto.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PatchMapping ("/{codigo}")
    public ResponseEntity<ProductoDTO> patch (@PathVariable String codigo, @RequestBody @Valid ProductoDTO productoDTO) {

        Optional<ProductoDTO> dto = productoService.parcialUpdate(codigo, productoDTO);

        if(dto.isPresent()) {
            return ResponseEntity.ok(dto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{codigo}")
    public ResponseEntity<Void> delete(
                                @PathVariable @Pattern(
                                        regexp = "^[0-9]{3}[A-Z]{1}$",
                                        message="El codigo debe tener 3 numeros y una letra"
                                )
                                        String codigo) {

        try{
            productoService.deleteByCodigo(codigo);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }


}
