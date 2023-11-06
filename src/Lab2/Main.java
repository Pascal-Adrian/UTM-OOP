package Lab2;

import Lab2.Behavior.Git;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        Git git = new Git();

        Thread runThread = new Thread(git::run);

        runThread.start();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(git::backgroundRun, 0, 5, TimeUnit.SECONDS);

        try {
            runThread.join();
        } catch (InterruptedException e) {
            System.out.println("Program interrupted.");
        } finally {
            // Shutdown the executor service when done
            executorService.shutdown();
        }
    }
}
