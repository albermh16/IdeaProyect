package es.daw.jakarta.jdbcapp.model;

import java.math.BigDecimal;

public class Producto {

    private Integer codigo;
    private String nombre;
    // Decimales para precio no recomendable por que son binarios en coma flotante
    // coma flotante pierde presion
    private BigDecimal precio;
    private Integer codigo_fabricante;
    private String nombre_fabricante;

    public Producto() {
    }

    public Producto(Integer codigo, Integer codigo_fabricante, BigDecimal precio, String nombre) {
        this.codigo = codigo;
        this.codigo_fabricante = codigo_fabricante;
        this.precio = precio;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(Integer codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_fabricante() {
        return nombre_fabricante;
    }

    public void setNombre_fabricante(String nombre_fabricante) {
        this.nombre_fabricante = nombre_fabricante;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", codigo_fabricante=" + codigo_fabricante +
                '}';
    }
}
