package com.qiwi.sleeping.barber.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by etrofimov on 06.09.17.
 */
public class CustomerListGenerator {

    public static List<Customer> generateCustomerList(BarberShop barberShop) {
        Random random = new Random();
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < random.nextInt(1000); i++) {
            customers.add(new Customer(barberShop));
        }
        return customers;
    }
}