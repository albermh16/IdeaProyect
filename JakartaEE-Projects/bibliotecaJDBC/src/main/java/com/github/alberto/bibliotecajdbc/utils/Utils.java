package com.github.alberto.bibliotecajdbc.utils;

import com.github.alberto.bibliotecajdbc.model.Autor;

import java.util.List;

public class Utils {
    public static String obtenerNombreAutor(List<Autor> autores, Long author_id){
        return autores.stream()
                .filter(a -> a.getId().equals(author_id))
                .findFirst()
                .map(Autor::getNombre)
                .orElse("Desconocido");

    }
}
