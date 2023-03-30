package com.ivanov.laboratory.utils;

import com.ivanov.laboratory.models.Task;
import com.ivanov.laboratory.services.TaskService;
import com.ivanov.laboratory.utils.Analyzators.Analyzator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.*;

public class ProcessingAnalyze implements ApplicationListener<ApplicationStartedEvent> {


    @Autowired
    private List<Analyzator> analyzatorList;

    @Autowired
    private Queue<Task> taskQueue;

    @Autowired
    private TaskService taskService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        taskQueue.addAll(taskService.findAllNotDoneTasks());
        while (true) {
            if (!taskQueue.isEmpty()) {
                Analyzator analyzator;
                if ((analyzator = findFreeAnalyzator()) != null) {
                    Task task = taskQueue.poll();
                    analyzator.setTask(task);
                    taskService.setTaskProcessing(task);
                    Thread thread = new Thread(new AnalyzatorThread(analyzator));
                    thread.start();
                }



            }
        }
    }

    private Analyzator findFreeAnalyzator() {
        for (Analyzator analyzator : analyzatorList) {
            if (analyzator.getTask() == null) {
                return analyzator;
            }
        }
        return null;
    }


}
