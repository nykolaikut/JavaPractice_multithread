package ua.education;

import java.util.HashMap;

public class Q {
    private HashMap<Integer,Integer> hm;
    private HashMap<Integer,Integer> sum;
    private String msg;

    public Q(){
        this.hm = new HashMap<Integer,Integer>();
        this.sum = new HashMap<Integer,Integer>();
        this.msg = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public HashMap<Integer, Integer> getHm() {
        return hm;
    }

    public void setHm(HashMap<Integer, Integer> hm) {
        this.hm = hm;
    }

    public HashMap<Integer, Integer> getSum() {
        return sum;
    }

    public void setSum(HashMap<Integer, Integer> sum) {
        this.sum = sum;
    }

}
