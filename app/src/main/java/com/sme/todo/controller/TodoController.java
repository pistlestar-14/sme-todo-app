package com.sme.todo.controller;

import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.dto.response.TodoListResponse;
import com.sme.todo.dto.response.TodoTaskResponse;
import com.sme.todo.service.TodoListService;
import com.sme.todo.service.TodoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoListService todoListService;
    private final TodoTaskService todoTaskService;

    @Autowired
    public TodoController(TodoListService todoListService, TodoTaskService todoTaskService) {
        this.todoListService = todoListService;
        this.todoTaskService = todoTaskService;
    }

    private static <T> ResponseEntity<T> from(HttpStatus status) {
        return from(null, status);
    }

    private static <T> ResponseEntity<T> from(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    @GetMapping("/todo")
    public ResponseEntity<List<TodoListResponse>> getAllTodoList() {
        return from(todoListService.getAllTodoList(), HttpStatus.OK);
    }

    @GetMapping("/todo/{todo-id}")
    public ResponseEntity<TodoListResponse> getTodoListById(@PathVariable("todo-id") @NotEmpty String todoListId) {
        return todoListService.getTodoListById(todoListId)
                .map(todoListResponse -> from(todoListResponse, HttpStatus.OK))
                .orElse(from(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/todo")
    public ResponseEntity<TodoListResponse> createTodoListById(@RequestBody TodoListCreateRequest todoListCreateRequest) {
        return todoListService.createTodoListById(todoListCreateRequest)
                .map(todoListResponse -> from(todoListResponse, HttpStatus.CREATED))
                .orElse(from(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/todo/{todo-id}")
    public ResponseEntity<TodoListResponse> updateTodoListById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                               @RequestBody TodoListUpdateRequest todoListUpdateRequest) {
        todoListUpdateRequest.setTodoListId(todoListId);
        return todoListService.updateTodoListById(todoListUpdateRequest)
                .map(todoListResponse -> from(todoListResponse, HttpStatus.OK))
                .orElse(from(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/todo/{todo-id}")
    public ResponseEntity<Void> deleteTodoListById(@PathVariable("todo-id") @NotEmpty String todoListId) {
        return todoListService.deleteTodoListById(todoListId) ? from(HttpStatus.OK) : from(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/todo/{todo-id}/task")
    public ResponseEntity<List<TodoTaskResponse>> getAllTodoTaskList(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                                     @RequestParam(name = "isDone", required = false) Boolean isDone) {
        return from(todoTaskService.getAllTodoTaskList(todoListId, isDone), HttpStatus.OK);
    }

    @GetMapping("/todo/{todo-id}/task/{task-id}")
    public ResponseEntity<TodoTaskResponse> getTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                            @PathVariable("task-id") @NotEmpty String todoTaskId) {
        return todoTaskService.getTodoTaskById(todoListId, todoTaskId)
                .map(todoTaskResponse -> from(todoTaskResponse, HttpStatus.OK))
                .orElse(from(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/todo/{todo-id}/task")
    public ResponseEntity<TodoTaskResponse> createTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                               @RequestBody TodoTaskCreateRequest todoTaskCreateRequest) {
        todoTaskCreateRequest.setTodoListId(todoListId);
        return todoTaskService.createTodoTaskById(todoTaskCreateRequest)
                .map(todoTaskResponse -> from(todoTaskResponse, HttpStatus.CREATED))
                .orElse(from(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/todo/{todo-id}/task/{task-id}")
    public ResponseEntity<TodoTaskResponse> updateTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                               @PathVariable("task-id") @NotEmpty String todoTaskId,
                                                               @RequestBody TodoTaskUpdateRequest todoTaskUpdateRequest) {
        todoTaskUpdateRequest.setTodoListId(todoListId);
        todoTaskUpdateRequest.setTodoTaskId(todoTaskId);
        return todoTaskService.updateTodoTaskById(todoTaskUpdateRequest)
                .map(todoTaskResponse -> from(todoTaskResponse, HttpStatus.OK))
                .orElse(from(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/todo/{todo-id}/task/{task-id}")
    public ResponseEntity<Void> deleteTodoTaskById(@PathVariable("todo-id") @NotEmpty String todoListId,
                                                   @PathVariable("task-id") @NotEmpty String todoTaskId) {
        return todoTaskService.deleteTodoTaskById(todoListId, todoTaskId) ? from(HttpStatus.OK) : from(HttpStatus.NOT_FOUND);
    }

}