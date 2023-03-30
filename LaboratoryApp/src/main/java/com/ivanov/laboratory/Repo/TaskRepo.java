package com.ivanov.laboratory.Repo;

import com.ivanov.laboratory.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findAllByDone(String a);
}
