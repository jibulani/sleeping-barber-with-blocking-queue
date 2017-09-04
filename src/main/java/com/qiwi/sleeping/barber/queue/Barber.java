package com.qiwi.sleeping.barber.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by etrofimov on 04.09.17.
 */
public class Barber extends Thread {

    private volatile boolean isSleeping = true;
    private BlockingQueue<Customer> customerBlockingQueue = new ArrayBlockingQueue<Customer>(10);

    public synchronized void setSleeping(boolean isSleeping) {
        this.isSleeping = isSleeping;
    }

    public synchronized boolean getIsSleeping() {
        return isSleeping;
    }

    public synchronized boolean increaseCustomers(Customer customer) {
        boolean result = customerBlockingQueue.offer(customer);
        if (result && isSleeping) {
            setSleeping(false);
        }
        System.out.println("Occupied chairs = " + getCustomerChairs());
        System.out.println("Barber isSleeping = " + getIsSleeping());
        return result;
    }

    public synchronized boolean decreaseCustomers(Customer customer) {
        boolean result = customerBlockingQueue.remove(customer);
        if (result && getCustomerChairs() == 0) {
            setSleeping(true);
        }
        System.out.println("Occupied chairs = " + getCustomerChairs());
        System.out.println("Barber isSleeping = " + getIsSleeping());
        return result;
    }

    private int getCustomerChairs() {
        return customerBlockingQueue.size();
    }

    @Override
    public void run() {
        do {
            if (!Thread.interrupted()) {
                if (customerBlockingQueue.isEmpty()) {
                    setSleeping(true);
                }

            } else  {
                return;
            }
        } while (true);
    }
}
