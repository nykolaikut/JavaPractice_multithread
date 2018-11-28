package ua.education;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RandomThread implements Runnable {

    private CountDownLatch latch;
    private Q q;
    private String name;
    private Thread t ;
    private int randomNumber;

    Random random = new Random();

    public RandomThread(String name, CountDownLatch latch, Q q)
    {
        this.name = name;
        this.latch = latch;
        this.q = q;
        t = new Thread(this, name);
        t.start();

    }

    @Override
    public void run()
    {
        try
        {

            latch.await();         //The thread keeps waiting till it is informed

            while(q.getMsg() == ""){
                while(true){
                    randomNumber = random.nextInt(Main.RANDOM_MAX_VALUE + 1);
                    if (randomNumber > 0) {
                        q.getHm().put(Integer.valueOf(name), randomNumber);
                        break;
                    }
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
