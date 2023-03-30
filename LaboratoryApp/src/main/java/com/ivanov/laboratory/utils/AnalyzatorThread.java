package com.ivanov.laboratory.utils;

import com.ivanov.laboratory.utils.Analyzators.Analyzator;

public class AnalyzatorThread implements Runnable {

    private final Analyzator analyzator;

    public AnalyzatorThread(Analyzator analyzator) {
        this.analyzator = analyzator;
    }

    @Override
    public void run() {
        try {
            System.out.println(analyzator.getTask().getAnalyze().getName() + " processing");
            Thread.sleep(10000); // analyzing....
            analyzator.getTaskService().setTaskDone(analyzator.getTask());
            System.out.println(analyzator.getTask().getAnalyze().getName() + " Done!! ");
            analyzator.setTask(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
