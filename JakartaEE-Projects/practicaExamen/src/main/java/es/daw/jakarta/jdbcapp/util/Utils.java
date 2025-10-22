package es.daw.jakarta.jdbcapp.util;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String obtenerNombreFabricante(List<Fabricante> fabricantes, Integer codigo) {

//        // FORMA 1: IMPERATIVA
//
//        for (Fabricante f: fabricantes) {
//            if (f.getCodigo().equals(codigo))
//                return f.getNombre();
//        }
//        return "desconocido";


        // FORMA 2: STREAM()
         return fabricantes.stream()
                 .filter(f -> f.getCodigo().equals(codigo))
                 .findFirst()
                 //.get().getNombre();
                 .map(Fabricante::getNombre)
                 .orElse("desconocido");
    }

    public static List<String> obtenerNombreProducto(List<Producto> productos, Integer codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo_fabricante().equals((codigo)))
                .sorted((p2, p1) -> p2.getNombre().compareToIgnoreCase(p1.getNombre()))
                .map(Producto::getNombre)
                .toList();
    }

}
