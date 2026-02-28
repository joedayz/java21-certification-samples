package org.example.io.serialization;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase para demostrar serialVersionUID.
 * Caso10: Cambiar a 2L, recompilar, y ejecutar Caso10 de nuevo para ver InvalidClassException.
 */
public class ProductVersioned implements Serializable {

    private static final long serialVersionUID = 1L;  // Cambiar a 2L, recompilar y ejecutar Caso10 para ver InvalidClassException

    private String name;
    private BigDecimal price;

    public ProductVersioned() {
    }

    public ProductVersioned(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
