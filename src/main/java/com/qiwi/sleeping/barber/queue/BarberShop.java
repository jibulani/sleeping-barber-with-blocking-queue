package com.qiwi.sleeping.barber.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by etrofimov on 06.09.17.
 */
public class BarberShop {

    private BlockingQueue<Customer> customerBlockingQueue = new ArrayBlockingQueue<Customer>(10);

    public synchronized void addToQueue(Customer customer) {
        while (customerBlockingQueue.size() >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        customerBlockingQueue.add(customer);
        System.out.println("Customer in queue");
        notify();
    }

    public synchronized void removeCustomerFromQueue() {
        while (customerBlockingQueue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        customerBlockingQueue.poll();
        System.out.println("Customer go out");
        notify();
    }

    public int getQueueSize() {
        return customerBlockingQueue.size();
    }
}
