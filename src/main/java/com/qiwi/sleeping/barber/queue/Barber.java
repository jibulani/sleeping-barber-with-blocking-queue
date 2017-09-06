package com.qiwi.sleeping.barber.queue;


/**
 * Created by etrofimov on 04.09.17.
 */
public class Barber extends Thread {

    private volatile boolean isSleeping = true;
    BarberShop barberShop;
    int counter = 0;

    Barber(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    public synchronized void setSleeping(boolean isSleeping) {
        this.isSleeping = isSleeping;
    }

    public synchronized boolean getIsSleeping() {
        return isSleeping;
    }


    @Override
    public void run() {
        do {
            if (barberShop.getQueueSize() == 0) {
                setSleeping(true);
                counter++;
            } else {
                if (getIsSleeping()) {
                    setSleeping(false);
                }
                barberShop.removeCustomerFromQueue();
                if (barberShop.getQueueSize() == 0) {
                    setSleeping(true);
                }
                System.out.println("Barber isSleeping = " + getIsSleeping());
            }
        } while (true);

    }
}
