package Lab2;

import Lab2.Behavior.Git;



public class Main {
    public static void main(String[] args) {
        Git git = new Git();

        Thread runThread = new Thread(git::run);
        Thread backgroundThread = new Thread(git::backgroundRun);

        runThread.start();
        backgroundThread.start();

        try {
            backgroundThread.join();
            runThread.join();
        } catch (InterruptedException e) {
            System.out.println("Program interrupted.");
        }
    }
}
