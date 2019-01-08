package ua.education;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class Container {

    private Map<Integer,Integer> sumMap;
    private LinkedBlockingQueue<Integer> queueRandom;
    private volatile boolean isProcessing;

    public Container(){
        this.sumMap = new HashMap<Integer,Integer>();
        this.queueRandom = new LinkedBlockingQueue<Integer>(300);
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

    public int getSum (int numberOfThread) {
        return sumMap.getOrDefault(numberOfThread,0);
    }

    public void putSum (int numberOfThread, int sum) {
        sumMap.put(numberOfThread, sum);
    }

    public Map<Integer, Integer> getSumMap() {
        return sumMap;
    }
}
