package com.qiwi.sleeping.barber.queue;

import java.util.List;

/**
 * Created by etrofimov on 04.09.17.
 */
public class App {

    public static void main(String[] args) {
        BarberShop barberShop = new BarberShop();
        Barber barber = new Barber(barberShop);
        List<Customer> customers = CustomerListGenerator.generateCustomerList(barberShop);

        barber.start();
        customers.forEach(Thread::start);

    }
}
