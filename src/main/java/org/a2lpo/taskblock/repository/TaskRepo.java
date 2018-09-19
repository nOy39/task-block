package org.a2lpo.taskblock.repository;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    /**
     * Метод поиска всех невыполненных задач до текущего дня + 1
     * @param currentDay Текущая дата с форматом времени ГГГГ-ММ-ДД 23:59:59
     * @param user пользователь чьи задачи ищет метод
     * @return список Task
     */
    @Query(
            value = "SELECT t from Task t " +
                    "where t.isDone = false " +
                    "and t.expiredDate < :tomorrow " +
                    "and t.user = :user " +
                    "order by t.id asc"
    )
    List<Task> findAllBeforeCurrentDay(@Param("tomorrow") LocalDateTime currentDay, @Param("user") User user);

    /**
     * Метод поиска всех невыполненных задач за указаный период
     * @param startDate Начальная дата
     * @param endDate Конечная дата
     * @param user пользователь чьи задачи ищет метод
     * @return список Task
     */
    @Query(
            value = "select t from Task t " +
                    "where t.isDone = false " +
                    "and t.expiredDate > :start " +
                    "and t.expiredDate < :endDate " +
                    "and t.user = :user"
    )
    List<Task> findAllInPeriod(@Param("start") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate,
                               @Param("user") User user);

    /**
     * Метод поиска всех задач пользователя
     * @param user
     * @return
     */
    @Query(value = "select t from Task t where t.user=:user")
    List<Task> findAll(@Param("user")User user);

    /**
     * Метод поиска задач по ID ищет в таблице по полям id и parent_id
     * @param id
     * @param user
     * @return
     */
    @Query(
            value = "select t from Task t " +
                    "where t.id = :id or t.parentId = :id " +
                    "and t.user = :user"
    )
    List<Task> findAllById (@Param("id") Long id, @Param("user") User user);
}
