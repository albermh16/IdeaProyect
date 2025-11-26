package es.daw.hotelesapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
@NoArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "tamano", nullable = false)
    private Integer tamano;

    @Column(name = "doble", nullable = false)
    private boolean doble;

    @Column(name = "precio_noche", nullable = false)
    private BigDecimal precio_noche;

    @Column(name = "incluye_desayuno", nullable = false)
    private boolean incluye_desayuno;

    @Column(name = "ocupada", nullable = false)
    private boolean ocupada;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;


}
