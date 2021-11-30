package com.sme.todo.util;

import com.sme.todo.constant.Priority;
import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.model.TodoList;
import com.sme.todo.model.TodoTask;

public class MockDto {

    public static final String TODO_LIST_ID = "todo-list-id";
    public static final String TODO_LIST_INVALID_ID = "todo-list-invalid-id";
    public static final String TODO_TASK_ID = "todo-task-id";
    public static final String TODO_TASK_INVALID_ID = "todo-task-invalid-id";

    private MockDto() {}

    public static TodoList prepareTodoList() {
        return prepareTodoList(TODO_LIST_ID);
    }

    public static TodoList prepareTodoList(String id) {
        return TodoList.builder()
                .todoListId(id)
                .title("title-" + id)
                .createdOn(DateUtil.timeNow())
                .lastUpdatedOn(DateUtil.timeNow())
                .build();
    }

    public static TodoListCreateRequest prepareTodoListCreateRequest(TodoList todoList) {
        return TodoListCreateRequest.builder()
                .title(todoList.getTitle())
                .build();
    }

    public static TodoListUpdateRequest prepareTodoListUpdateRequest(TodoList todoList) {
        return TodoListUpdateRequest.builder()
                .todoListId(todoList.getTodoListId())
                .title(todoList.getTitle())
                .build();
    }

    public static TodoTask prepareTodoTask() {
        return prepareTodoTask(TODO_TASK_ID);
    }

    public static TodoTask prepareTodoTask(String id) {
        return prepareTodoTask(TODO_LIST_ID, id);
    }

    public static TodoTask prepareTodoTask(String listId, String id) {
        return TodoTask.builder()
                .todoListId(listId)
                .todoTaskId(id)
                .title("title-" + id)
                .description("desc-" + id)
                .priority(Priority.HIGH.getType())
                .isDone(false)
                .dueDate(DateUtil.timeNow().plusDays(1))
                .createdOn(DateUtil.timeNow())
                .lastUpdatedOn(DateUtil.timeNow())
                .build();
    }

    public static TodoTaskCreateRequest prepareTodoTaskCreateRequest(TodoTask todoTask) {
        return TodoTaskCreateRequest.builder()
                .todoListId(todoTask.getTodoListId())
                .title(todoTask.getTitle())
                .description(todoTask.getDescription())
                .priority(Priority.fromValue(todoTask.getPriority()))
                .dueDate(DateUtil.toEpochMilli(todoTask.getDueDate()))
                .build();
    }

    public static TodoTaskUpdateRequest prepareTodoTaskUpdateRequest(TodoTask todoTask) {
        return TodoTaskUpdateRequest.builder()
                .todoListId(todoTask.getTodoListId())
                .todoTaskId(todoTask.getTodoTaskId())
                .title(todoTask.getTitle())
                .description(todoTask.getDescription())
                .priority(Priority.fromValue(todoTask.getPriority()))
                .dueDate(DateUtil.toEpochMilli(todoTask.getDueDate()))
                .isDone(todoTask.getIsDone())
                .build();
    }
}
