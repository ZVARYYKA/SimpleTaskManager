package com.zvaryyka.simpletaskmanager.services;

import com.zvaryyka.simpletaskmanager.models.Task;
import com.zvaryyka.simpletaskmanager.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    //TODO добавление необходимых методов для работы с задачами в базе данных
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> findPersonTask(int id) {
        return taskRepository.findAllByUserId(id);
    }
    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }
    @Transactional
    public void update(int id,Task task) {
        task.setId(id);
        taskRepository.save(task);
    }
    @Transactional
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}
