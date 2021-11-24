package com.sme.todo.dto.response;

import com.sme.todo.model.TodoList;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Value(staticConstructor = "of")
public class TodoListResponse implements Serializable {
    @NotNull String todoListId;
    @NotEmpty String title;

    public static TodoListResponse from(TodoList todoList) {
        return TodoListResponse.of(todoList.getTodoListId(), todoList.getTitle());
    }
}
