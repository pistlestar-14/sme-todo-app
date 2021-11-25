package com.sme.todo.controller;

import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.dto.response.TodoTaskResponse;
import com.sme.todo.service.TodoTaskService;
import com.sme.todo.util.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/api/v1/todo/{todo-id}", produces = {"application/json"})
public class TodoTaskController {

    private final TodoTaskService todoTaskService;

    @Autowired
    public TodoTaskController(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    @Operation(summary = "Get all todo task for a todo list in order from highest to lowest priority")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Valid request",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoTaskResponse.class))})})
    @GetMapping("/task")
    public ResponseEntity<List<TodoTaskResponse>> getAllTodoTaskList(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId,
            @Parameter(description = "Controls whether to return, done tasks or not. When not given returns all tasks")
            @RequestParam(name = "returnDoneTasks", required = false) Boolean isDone) {
        return Util.from(todoTaskService.getAllTodoTaskList(todoListId, isDone), HttpStatus.OK);
    }

    @Operation(summary = "Get a todo task by id for a todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the task",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoTaskResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found the task",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/task/{task-id}")
    public ResponseEntity<TodoTaskResponse> getTodoTaskById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId,
            @Parameter(description = "todo task id")
            @PathVariable("task-id") @NotEmpty String todoTaskId) {
        return todoTaskService.getTodoTaskById(todoListId, todoTaskId)
                .map(todoTaskResponse -> Util.from(todoTaskResponse, HttpStatus.OK))
                .orElse(Util.from(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a todo task for a todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Task created successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoTaskResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request data is invalid",
                    content = @Content(mediaType = "application/json"))})
    @PostMapping("/task")
    public ResponseEntity<TodoTaskResponse> createTodoTaskById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId,
            @RequestBody TodoTaskCreateRequest todoTaskCreateRequest) {
        todoTaskCreateRequest.setTodoListId(todoListId);
        return todoTaskService.createTodoTaskById(todoTaskCreateRequest)
                .map(todoTaskResponse -> Util.from(todoTaskResponse, HttpStatus.CREATED))
                .orElse(Util.from(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Update a todo task for a todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the task",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoTaskResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request data is invalid",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/task/{task-id}")
    public ResponseEntity<TodoTaskResponse> updateTodoTaskById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId,
            @Parameter(description = "todo task id")
            @PathVariable("task-id") @NotEmpty String todoTaskId,
            @RequestBody TodoTaskUpdateRequest todoTaskUpdateRequest) {
        todoTaskUpdateRequest.setTodoListId(todoListId);
        todoTaskUpdateRequest.setTodoTaskId(todoTaskId);
        return todoTaskService.updateTodoTaskById(todoTaskUpdateRequest)
                .map(todoTaskResponse -> Util.from(todoTaskResponse, HttpStatus.OK))
                .orElse(Util.from(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Delete a todo task from a todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the task",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found the task",
                    content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/task/{task-id}")
    public ResponseEntity<Void> deleteTodoTaskById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId,
            @Parameter(description = "todo task id")
            @PathVariable("task-id") @NotEmpty String todoTaskId) {
        return todoTaskService.deleteTodoTaskById(todoListId, todoTaskId)
                ? Util.from(HttpStatus.OK)
                : Util.from(HttpStatus.NOT_FOUND);
    }

}