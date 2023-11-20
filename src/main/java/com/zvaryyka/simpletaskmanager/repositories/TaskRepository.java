package com.zvaryyka.simpletaskmanager.repositories;

import com.zvaryyka.simpletaskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    //Todo добавить методы если понадобятся
    List<Task> findAllByUserId(int id);
    Task findById (int id);
}
