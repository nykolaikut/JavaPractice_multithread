package ua.education;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Main {
    public final static int NUM_RANDOM_THREADS = 7;
    public final static int WINNING_MIN_SUM = 100;
    public final static int RANDOM_MAX_VALUE = 5;
    private final static int NUM_SUM_THREADS = 3;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Q q = new Q();

        int numberOfCycles = 0;

        for (int i = 1; i <= NUM_RANDOM_THREADS; i++) {
            new RandomThread(String.valueOf(i), latch, q);
        }
        for (int i = 1; i <= NUM_SUM_THREADS; i++) {
            new SumThread(String.valueOf(i), latch, q);
        }

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println( "Threads started");
        latch.countDown();          //This will inform all the threads to start

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( "Threads are informed");

        Set<Map.Entry<Integer,Integer>> setHm = q.getHm().entrySet();
        Set<Map.Entry<Integer,Integer>> setSum = q.getSum().entrySet();

        while(q.getMsg() == ""){

            for (Map.Entry<Integer, Integer> me : setHm) {
                System.out.print("Thread rnd" + me.getKey() + " : ");
                System.out.println(me.getValue());
            }

            for (Map.Entry<Integer, Integer> me : setSum) {
                System.out.print("Thread sum" + me.getKey() + " : ");
                System.out.println(me.getValue());
            }
            System.out.println("-------------------------------------");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numberOfCycles++;
        }

        System.out.println("Number of cycles : " + numberOfCycles);
        System.out.println(q.getMsg());
    }
}
