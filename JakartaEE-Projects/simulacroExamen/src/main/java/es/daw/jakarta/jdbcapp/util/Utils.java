package es.daw.jakarta.jdbcapp.util;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;

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

    public static List<String> obtenerNombresProductosPorFabricante(List<Producto> productos, Integer codigoFabricante) {
        return productos.stream()
                .filter(p -> p.getCodigo_fabricante().equals(codigoFabricante))
                .map(Producto::getNombre)
                .toList(); // o .collect(Collectors.toList()) si usas Java 16 o inferior
    }


}
