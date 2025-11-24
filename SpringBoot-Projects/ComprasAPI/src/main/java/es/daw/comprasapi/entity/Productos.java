package es.daw.comprasapi.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
//@JsonIgnoreProperties({"compras"})
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private String categoria;

    @ManyToMany(mappedBy = "listaProductos")
    private List<Compras> compras;

    public Productos() {
        compras = new ArrayList<>();
    }

    public void addCompra(Compras compra) {
        compras.add(compra);
        compra.getListaProductos().add(this);
    }

    public void removeCompra(Compras compra) {
        compras.remove(compra);
        compra.getListaProductos().remove(this);
    }
}