package org.example;

public class MultiThreads implements Runnable {

    private int threadNumber;
    private int lenProgressBar;
    private char incomplete = '.';
    private char complete = '#';

    public MultiThreads(int threadNumber, int lenProgressBar) {
        this.threadNumber = threadNumber;
        this.lenProgressBar = lenProgressBar;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();
        StringBuilder progress = new StringBuilder();
        for (int i = 0; i <= lenProgressBar; i++) {
            progress.append(".");
        }
        for (int i = 0; i <= lenProgressBar; i++) {
            progress.setCharAt(i, complete);
            System.out.printf("TH %d (id: %d): [%s]\n", threadNumber, threadId, progress);

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.printf("Thread %d (id: %d) finished in %d ms\n", threadNumber, threadId, totalTime);
    }
}
