package ua.education;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SumThread implements Runnable {
    private CountDownLatch latch;
    private Q q;
    private String name;
    private Thread t ;
    private int randomNumber;

    Random random = new Random();

    public SumThread(String name, CountDownLatch latch, Q q)
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
            latch.await(); //The thread keeps waiting till it is informed

 //           Thread.sleep(10);

            int sumOfThread;
            int sumAll;
            int rndValue;

            while(q.getMsg() == ""){
                while(true){
                    randomNumber = random.nextInt( Main.NUM_RANDOM_THREADS);
                    if (randomNumber > 0) {
                        if (q.getSum().get(Integer.valueOf(name)) == null) {
                            sumOfThread = 0;
                        } else {
                            sumOfThread = q.getSum().get(Integer.valueOf(name));
                        }

                        if (q.getHm().get(randomNumber) == null ) {
                            rndValue = 0;
                        } else {
                            rndValue = q.getHm().get(randomNumber);
                        }

                        sumAll = sumOfThread + rndValue;
                        q.getSum().put(Integer.valueOf(name), sumAll);
                        break;
                    }
                }
                if (sumAll >= Main.WINNING_MIN_SUM) {
                    q.setMsg("Result: Thread sum" + name + " have won. The sum is " + sumAll);
                    break;
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
