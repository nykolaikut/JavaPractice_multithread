package ua.education;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Container {

    private HashMap<Integer,Integer> sumMap;
    private LinkedBlockingQueue<Integer> queueRandom;
    private boolean isProcessing;

    public Container(){
        this.sumMap = new HashMap<Integer,Integer>();
        this.queueRandom = new LinkedBlockingQueue<Integer>(500);
        this.isProcessing = true;

    }

    public boolean getIsProcessing() {
        return this.isProcessing;
    }

    public void interruptProcessing(){
        this.isProcessing = false;
    }

    public void put(int randomNumber) {
        try {
            queueRandom.put(randomNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LinkedBlockingQueue<Integer> getQueueRandom() {
        return queueRandom;
    }

    public void putSum (int numberOfThread){
        int sumThread = (sumMap.get(numberOfThread)== null) ? 0 : sumMap.get(numberOfThread);
        int randomNumber = 0;

        try {
            randomNumber = queueRandom.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int result = sumThread + randomNumber;
        sumMap.put(numberOfThread,sumThread + randomNumber);
        if (result >= Main.WINNING_MIN_SUM) interruptProcessing();
    }

    public HashMap<Integer, Integer> getSumMap() {
        return sumMap;
    }
}
