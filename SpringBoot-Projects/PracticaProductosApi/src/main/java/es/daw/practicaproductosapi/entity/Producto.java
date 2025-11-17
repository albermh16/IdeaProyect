package es.daw.practicaproductosapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=4, unique=true)
    private String codigo;

    @Column(nullable=false, length=255)
    private String nombre;

    @Column(nullable=false, precision=10, scale=2)
    private BigDecimal precio;

    @ManyToOne(optional=false)
    @JoinColumn(name = "codigo_fabricante", nullable=false)
    private Fabricante fabricante;


}
