package com.sme.todo.controller;

import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.response.TodoListResponse;
import com.sme.todo.service.TodoListService;
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
@RequestMapping(path = "/api/v1/todo", produces = {"application/json"})
public class TodoListController {

    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @Operation(summary = "Get all todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Valid request",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListResponse.class))})})
    @GetMapping
    public ResponseEntity<List<TodoListResponse>> getAllTodoList() {
        return Util.from(todoListService.getAllTodoList(), HttpStatus.OK);
    }

    @Operation(summary = "Get a todo list by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the todo list",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found the todo list",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/{todo-id}")
    public ResponseEntity<TodoListResponse> getTodoListById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId) {
        return todoListService.getTodoListById(todoListId)
                .map(todoListResponse -> Util.from(todoListResponse, HttpStatus.OK))
                .orElse(Util.from(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Todo list created successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request data is invalid",
                    content = @Content(mediaType = "application/json"))})
    @PostMapping
    public ResponseEntity<TodoListResponse> createTodoListById(@RequestBody TodoListCreateRequest todoListCreateRequest) {
        return todoListService.createTodoListById(todoListCreateRequest)
                .map(todoListResponse -> Util.from(todoListResponse, HttpStatus.CREATED))
                .orElse(Util.from(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Update a todo task list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the todo list",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request data is invalid",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/{todo-id}")
    public ResponseEntity<TodoListResponse> updateTodoListById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId,
            @RequestBody TodoListUpdateRequest todoListUpdateRequest) {
        todoListUpdateRequest.setTodoListId(todoListId);
        return todoListService.updateTodoListById(todoListUpdateRequest)
                .map(todoListResponse -> Util.from(todoListResponse, HttpStatus.OK))
                .orElse(Util.from(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Delete a todo list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Found the todo list, no Content to return.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found the todo list",
                    content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/{todo-id}")
    public ResponseEntity<Void> deleteTodoListById(
            @Parameter(description = "todo list id")
            @PathVariable("todo-id") @NotEmpty String todoListId) {
        return todoListService.deleteTodoListById(todoListId)
                ? Util.from(HttpStatus.NO_CONTENT)
                : Util.from(HttpStatus.NOT_FOUND);
    }

}