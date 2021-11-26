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
    public static final String TODO_TASK_ID = "todo-task-id";

    private MockDto() {}

    public static TodoList prepareTodoList() {
        return TodoList.builder()
                .todoListId(TODO_LIST_ID)
                .title("title-" + TODO_LIST_ID)
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
        return TodoTask.builder()
                .todoListId(TODO_LIST_ID)
                .todoTaskId(TODO_TASK_ID)
                .title("title-" + TODO_TASK_ID)
                .description("desc-" + TODO_TASK_ID)
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
