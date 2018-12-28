package ua.education;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SumThread implements Runnable {
    private CountDownLatch latch;
    private Container container;
    private Integer numberOfThread;
    private Thread t ;
    private int randomNumber;

    Random random = new Random();

    public SumThread(Integer numberOfThread, CountDownLatch latch, Container container)
    {
        this.latch = latch;
        this.container = container;
        this.numberOfThread = numberOfThread;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        try {
            latch.await();

            while (container.getIsProcessing()) {
                container.putSum(numberOfThread);
                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     }
}
