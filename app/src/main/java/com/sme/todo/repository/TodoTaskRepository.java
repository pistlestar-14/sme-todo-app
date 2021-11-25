package com.sme.todo.repository;

import com.sme.todo.model.TodoTask;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoTaskRepository extends PagingAndSortingRepository<TodoTask, String> {
    Optional<TodoTask> findByTodoListIdAndTodoTaskId(String todoListId, String todoTaskId);
    List<TodoTask> findByTodoListIdOrderByPriorityDesc(String todoListId);
    List<TodoTask> findByTodoListIdAndIsDoneOrderByPriorityDesc(String todoListId, Boolean isDone);
    void deleteByTodoListId(String todoListId);
}
