package com.sme.todo.controller;

import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.dto.response.TodoListResponse;
import com.sme.todo.dto.response.TodoTaskResponse;
import com.sme.todo.service.TodoListService;
import com.sme.todo.service.TodoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoListService todoListService;
    private final TodoTaskService todoTaskService;

    @Autowired
    public TodoController(TodoListService todoListService, TodoTaskService todoTaskService) {
        this.todoListService = todoListService;
        this.todoTaskService = todoTaskService;
    }

    @GetMapping("/todo")
    public List<TodoListResponse> getAllTodoList() {
        return todoListService.getAllTodoList();
    }

    @GetMapping("/todo/{todo-id}")
    public Optional<TodoListResponse> getTodoListById(@PathVariable("todo-id") @NotEmpty String todoListId) {
        return todoListService.getTodoListById(todoListId);
    }

    @PostMapping("/todo")
    public Optional<TodoListResponse> createTodoListById(@RequestBody TodoListCreateRequest todoListCreateRequest) {
        return todoListService.createTodoListById(todoListCreateRequest);
    }

    @PutMapping("/todo/{todo-id}")
    public Optional<TodoListResponse> updateTodoListById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                         @RequestBody TodoListUpdateRequest todoListUpdateRequest) {
        todoListUpdateRequest.setTodoListId(todoListId);
        return todoListService.updateTodoListById(todoListUpdateRequest);
    }

    @DeleteMapping("/todo/{todo-id}")
    public void deleteTodoListById(@PathVariable("todo-id") @NotEmpty String todoListId) {
        todoListService.deleteTodoListById(todoListId);
    }

    @GetMapping("/todo/{todo-id}/task")
    public List<TodoTaskResponse> getAllTodoTaskList(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                     @RequestParam(name = "isDone", required = false) Boolean isDone) {
        return todoTaskService.getAllTodoTaskList(todoListId, isDone);
    }

    @GetMapping("/todo/{todo-id}/task/{task-id}")
    public Optional<TodoTaskResponse> getTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                      @PathVariable("task-id") @NotEmpty String todoTaskId) {
        return todoTaskService.getTodoTaskById(todoListId, todoTaskId);
    }

    @PostMapping("/todo/{todo-id}/task")
    public Optional<TodoTaskResponse> createTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                         @RequestBody TodoTaskCreateRequest todoTaskCreateRequest) {
        todoTaskCreateRequest.setTodoListId(todoListId);
        return todoTaskService.createTodoTaskById(todoTaskCreateRequest);
    }

    @PutMapping("/todo/{todo-id}/task/{task-id}")
    public Optional<TodoTaskResponse> updateTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                         @PathVariable("task-id") @NotEmpty String todoTaskId,
                                                         @RequestBody TodoTaskUpdateRequest todoTaskUpdateRequest) {
        todoTaskUpdateRequest.setTodoListId(todoListId);
        todoTaskUpdateRequest.setTodoTaskId(todoTaskId);
        return todoTaskService.updateTodoTaskById(todoTaskUpdateRequest);
    }

    @DeleteMapping("/todo/{todo-id}/task/{task-id}")
    public void deleteTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                   @PathVariable("task-id") @NotEmpty String todoTaskId) {
        todoTaskService.deleteTodoTaskById(todoListId, todoTaskId);
    }

}