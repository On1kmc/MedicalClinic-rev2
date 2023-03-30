package com.ivanov.laboratory.services;

import com.ivanov.laboratory.Repo.TaskRepo;
import com.ivanov.laboratory.dto.TaskDTOForStatus;
import com.ivanov.laboratory.models.Analyze;
import com.ivanov.laboratory.models.Order;
import com.ivanov.laboratory.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    private final Queue<Task> taskQueue;

    @Autowired
    public TaskService(TaskRepo taskRepo, Queue<Task> taskQueue) {
        this.taskRepo = taskRepo;
        this.taskQueue = taskQueue;
    }

    public void createTaskListToOrder(Order order) {
        List<Task> taskList = new ArrayList<>();
        for (Analyze analyze : order.getAnalyzeList()) {
            Task task = new Task();
            task.setOrder(order);
            task.setDone("Waiting");
            task.setAnalyze(analyze);
            taskList.add(task);
        }
        saveTasksList(taskList);
    }

    public List<Task> findAllNotDoneTasks() {
        return taskRepo.findAllByDone("Waiting");
    }


    @Transactional
    public void setTaskDone(Task task) {
        task.setDone("Done");
        taskRepo.save(task);
    }

    @Transactional
    public void setTaskProcessing(Task task) {
        task.setDone("Processing");
        taskRepo.save(task);
    }

    @Transactional
    public void saveTasksList(List<Task> taskList) {
        taskRepo.saveAll(taskList);
        taskQueue.addAll(taskList);
    }

    public List<TaskDTOForStatus> getTaskDTOListForResponse(Order order) {
        ArrayList<TaskDTOForStatus> statusList = new ArrayList<>();
        List<Task> taskList = order.getTaskList();
        taskList.forEach(task -> {
            TaskDTOForStatus status = new TaskDTOForStatus();
            status.setName(task.getAnalyze().getName());
            status.setStatus(task.getDone());
            statusList.add(status);
        });
        return statusList;
    }
}
