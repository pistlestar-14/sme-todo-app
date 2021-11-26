package com.sme.todo.service;

import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.dto.response.TodoTaskResponse;

import java.util.List;
import java.util.Optional;

public interface TodoTaskService {
    List<TodoTaskResponse> getAllTodoTaskList(String todoListId, Boolean isDone);
    Optional<TodoTaskResponse> getTodoTaskById(String todoListId, String todoTaskId);
    Optional<TodoTaskResponse> createTodoTaskById(TodoTaskCreateRequest todoTaskCreateRequest);
    Optional<TodoTaskResponse> updateTodoTaskById(TodoTaskUpdateRequest todoTaskUpdateRequest);
    Boolean deleteTodoTaskById(String todoListId, String todoTaskId);
}
