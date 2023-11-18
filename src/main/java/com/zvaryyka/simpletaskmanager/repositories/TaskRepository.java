package com.zvaryyka.simpletaskmanager.repositories;

import com.zvaryyka.simpletaskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    //Todo добавить методы если понадобятся
}
