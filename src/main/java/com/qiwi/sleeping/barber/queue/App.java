package com.qiwi.sleeping.barber.queue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by etrofimov on 04.09.17.
 */
public class App {

    static Barber barber;

    public static void main(String[] args) {
        barber = new Barber();
        Customer customer = new Customer(barber);
        barber.start();
        ArrayList<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            customers.add(new Customer(barber));
        }
        System.out.println("In main thread: Barber isSleeping = " + barber.getIsSleeping());
        customers.forEach(Thread::start);
        System.out.println("In main thread: Barber isSleeping = " + barber.getIsSleeping());
        customer.start();
        customer.interrupt();
        customers.forEach(Thread::interrupt);
        try {
            customer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customers.forEach(customer1 -> {
            try {
                customer1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("In main thread: Barber isSleeping = " + barber.getIsSleeping());
        ArrayList<Customer> customers2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customers2.add(new Customer(barber));
        }
        customers2.forEach(Thread::start);
        customers2.forEach(Thread::interrupt);
        customers2.forEach(customer1 -> {
            try {
                customer1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("In main thread: Barber isSleeping = " + barber.getIsSleeping());
        barber.interrupt();

    }
}
