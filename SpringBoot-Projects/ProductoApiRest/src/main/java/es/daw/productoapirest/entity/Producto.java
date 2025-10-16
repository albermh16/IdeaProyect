package es.daw.productoapirest.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    // Relación muchos a uno con Fabricante
    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_fabricante", nullable = false)
    private Fabricante fabricante;

}