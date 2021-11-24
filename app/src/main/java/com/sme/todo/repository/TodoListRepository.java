package com.sme.todo.repository;

import com.sme.todo.model.TodoList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends PagingAndSortingRepository<TodoList, String> {
    List<TodoList> findAllByOrderByCreatedOnAsc();
}
