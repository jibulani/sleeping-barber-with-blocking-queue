package com.qiwi.sleeping.barber.queue;

/**
 * Created by etrofimov on 04.09.17.
 */
public class Customer extends Thread {

    private Barber barber;

    public Customer(Barber barber) {
        super();
        this.barber = barber;
    }

    @Override
    public void run() {
        if (barber.increaseCustomers(this)) {
            System.out.println("Customer added in queue");
            do {
                if (Thread.interrupted()) {
                    if (barber.decreaseCustomers(this)) {
                        System.out.println("Customer go out");
                    }
                    return;
                }
            } while (true);
        } else {
            System.out.println("No free chairs, customer go out");
        }

    }
}
