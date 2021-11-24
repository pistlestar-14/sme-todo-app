package com.sme.todo.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class TodoListUpdateRequest extends TodoListCreateRequest {
    private String todoListId;
}
