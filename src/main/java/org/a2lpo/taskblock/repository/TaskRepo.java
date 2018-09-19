package org.a2lpo.taskblock.repository;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.isDone = false")
    List<Task> findAllIsActive();

    @Query(value = "SELECT t FROM Task t where t.parentId = null")
    List<Task> findAllByParentNull();

    @Query(value = "SELECT t from Task t where t.isDone = false and t.expiredDate < :tomorrow and t.user = :user order by t.id asc")
    List<Task> findAllCurrentDayTask(@Param("tomorrow") LocalDateTime currentDay, @Param("user") User user);

    @Query(value = "select t from Task t where t.isDone = false and t.expiredDate > :start and t.expiredDate < :endDate and t.user = :user")
    List<Task> findAllTaskFromStartToEnd(@Param("start") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate,
                                         @Param("user") User user);

    @Query(value = "select t from Task t where t.isDone = false and t.expiredDate < :endDate")
    List<Task> findAllBeforeExpiredDate(@Param("endDate") LocalDateTime endDate);

    @Query(value = "select t from Task t where t.id = :id or t.parentId = :id and t.user = :user")
    List<Task> findAllById (@Param("id") Long id,
                            @Param("user") User user);

    @Query(value = "select t from Task t where t.user=:user")
            List<Task> findAll(@Param("user")User user);
    /**
     * Счётчик дочерних задач
     *
     * @param task В параметры поиска передается родительская задача
     * @return - возвращает long
     */
    Long countByParentId(Task task);

    List<Task> findAllByExpiredDateIsIn(LocalDateTime localDateTime);
}
