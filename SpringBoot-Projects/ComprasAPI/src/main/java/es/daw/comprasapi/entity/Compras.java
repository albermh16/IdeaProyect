package es.daw.comprasapi.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
@Data
@AllArgsConstructor
//@JsonIgnoreProperties({"listaProductos"})
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private double total;

    @ManyToMany
    @JoinTable(
            name = "compra_producto",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )

    private List<Productos> listaProductos;

    public Compras() {
        listaProductos = new ArrayList<>();
    }

    public void addProducto(Productos producto) {
        listaProductos.add(producto);
        producto.getCompras().add(this);
    }

    public void removeProducto(Productos producto) {
        listaProductos.remove(producto);
        producto.getCompras().remove(this);
    }

}