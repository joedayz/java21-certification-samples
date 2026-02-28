package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FlatMappingYFiltering {

    static void main() {

        /*
            // SIN flatMapping - obtienes listas anidadas
            Joe -> [[Tea, Cake], [Coffee, Cake]]

            // CON flatMapping - aplana todo en un solo Set
            Joe -> [Tea, Coffee, Cake]
         */
        Customer joe = new Customer("Joe");
        Customer bob = new Customer("Bob");

        Product tea = new Product("Tea", 1.99);
        Product coffee = new Product("Coffee", 1.99);
        Product cake = new Product("Cake", 2.99);


        List<Order> orders = List.of(
                new Order(joe, LocalDate.of(2018, 11, 21), List.of(tea, cake)),
                new Order(bob, LocalDate.of(2018, 11, 21), List.of(coffee)),
                new Order(joe, LocalDate.of(2018, 11, 22), List.of(coffee, cake))
        );

        // Productos por cliente: {Joe=[Tea, Coffee, Cake], Bob=[Coffee]}
        Map<Customer, Set<Product>> customerProducts =
                orders.stream()
                        .collect(Collectors.groupingBy(o -> o.getCustomer(),
                                Collectors.flatMapping(o -> o.getItems().stream(),
                                        Collectors.toSet())));

        System.out.println(customerProducts);

        // Órdenes por cliente filtradas por fecha 2018-11-22
// {Joe=[Order[date=2018-11-22, customer Joe, products=[Coffee, Cake]]], Bob=[]}
        Map<Customer, Set<Order>> customerOrdersOnDate =
                orders.stream()
                        .collect(Collectors.groupingBy(o -> o.getCustomer(),
                                Collectors.filtering(o -> o.getDate().equals(LocalDate.of(2018, 11, 22)),
                                        Collectors.toSet())));

        System.out.println(customerOrdersOnDate);
    }
}
