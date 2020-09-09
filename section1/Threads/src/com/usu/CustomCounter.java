package com.usu;

public class CustomCounter implements Runnable {
    private int max;
    private int count = 0;
    public CustomCounter(int max) {
        this.max = max;
        new Thread(this).start();
    }
    public void run() {
        while (count < max) {
            count ++;
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
