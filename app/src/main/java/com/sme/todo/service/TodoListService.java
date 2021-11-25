package com.sme.todo.service;

import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.response.TodoListResponse;

import java.util.List;
import java.util.Optional;

public interface TodoListService {
    List<TodoListResponse> getAllTodoList();
    Optional<TodoListResponse> getTodoListById(String todoListId);
    Optional<TodoListResponse> createTodoListById(TodoListCreateRequest todoListCreateRequest);
    Optional<TodoListResponse> updateTodoListById(TodoListUpdateRequest todoListUpdateRequest);
    Boolean deleteTodoListById(String todoListId);
}
