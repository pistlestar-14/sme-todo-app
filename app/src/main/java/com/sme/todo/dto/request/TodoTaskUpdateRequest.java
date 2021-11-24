package com.sme.todo.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
public class TodoTaskUpdateRequest extends TodoTaskCreateRequest {
    @NotEmpty private String todoTaskId;
    private Boolean isDone;
}
