package com.qiwi.sleeping.barber.queue;

/**
 * Created by etrofimov on 04.09.17.
 */
public class Customer extends Thread {

    BarberShop barberShop;

    public Customer(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    @Override
    public void run() {
        if (barberShop.getQueueSize() < 10) {
            barberShop.addToQueue(this);
        } else {
            System.out.println("No place in barbershop");
        }
    }
}
