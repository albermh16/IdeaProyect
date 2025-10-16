package es.daw.productoapirest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
//No meter @Data ni @toString por que provocaria un bucle infinito
//@Table(name = "fabricante")
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, length = 255)
    private String nombre;

    // Relación 1:N con Producto
    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

}