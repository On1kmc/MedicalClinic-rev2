package com.ivanov.laboratory.utils.Analyzators;

import com.ivanov.laboratory.models.Task;
import com.ivanov.laboratory.services.TaskService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Analyzator {

    private Task task;

    @Autowired
    private TaskService taskService;

}
