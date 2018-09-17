package org.a2lpo.taskblock.repository;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByUserAndSubTaskNull(User user);

    List<Task> findBySubTask(Task task);

    Long countAllByUserAndSubTaskNull(User user);

    /**
     * Счётчик дочерних задач
     * @param task В параметры поиска передается родительская задача
     * @return - возвращает long
     */
    Long countDistinctBySubTask(Task task);
}
