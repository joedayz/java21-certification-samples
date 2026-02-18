package org.example.interfacesfuncionales;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static void main() {
        List<Product> products = new ArrayList<>();

        products.stream()
                .filter(p -> p.getDiscount()==0)
                .peek(p -> p.applyDiscount(0.1))
                .map(p -> p.getBestBefore())
                .forEach(d -> d.plusDays(1));
    }
}
