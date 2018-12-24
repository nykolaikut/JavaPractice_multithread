package ua.education;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Main {

    public final static int NUMBER_RANDOM_THREADS = 7;
    public final static int WINNING_MIN_SUM = 100;
    public final static int RANDOM_MAX_VALUE = 5;
    private final static int NUMBER_SUM_THREADS = 3;


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Container container = new Container();

        for (int i = 1; i <= NUMBER_RANDOM_THREADS; i++) {
            new RandomThread(i, latch, container);
        }
        for (int i = 1; i <= NUMBER_SUM_THREADS; i++) {
            new SumThread(i, latch, container);
        }

        latch.countDown();

        while (container.getIsProcessing()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Set<Map.Entry<Integer,Integer>> setSum = container.getSumMap().entrySet();

        for (Map.Entry<Integer, Integer> result : setSum) {
            System.out.print("Thread sum " + result.getKey() + " : The summa is ");
            System.out.println(result.getValue());
        }
    }
}
