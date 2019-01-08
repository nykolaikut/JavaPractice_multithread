package ua.education;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RandomThread implements Runnable {
    private CountDownLatch latch;
    private Container container;
    private Integer numberOfThread;
    private Thread t ;
    private int randomNumber;

    Random random = new Random();

    public RandomThread(Integer numberOfThread, CountDownLatch latch, Container container)
    {
        this.numberOfThread = numberOfThread;
        this.latch = latch;
        this.container = container;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        try
        {
            latch.await();

            while (container.getIsProcessing()) {

                container.put(random.nextInt(Main.RANDOM_MAX_VALUE ) + 1);

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
