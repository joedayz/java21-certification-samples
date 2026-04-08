package org.example.genericos;

import java.util.ArrayList;
import java.util.List;

public class GenericsMain {

    public static void main(String[] args) {
        Product[] products =  new Food[10];
        //products[0] = new Drink();

        List<Product> productList = new ArrayList<>();



        List<Food> foodList = new ArrayList<Food>();
        List values = foodList;

        List<Product> productList1 = values;
        productList1.add(new Drink());

        Drink drink = (Drink) productList1.get(0);
        Food x2 = foodList.get(0);

    }
}
