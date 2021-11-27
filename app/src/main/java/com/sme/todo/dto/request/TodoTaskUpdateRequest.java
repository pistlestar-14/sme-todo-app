package com.sme.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sme.todo.constant.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class TodoTaskUpdateRequest implements Serializable {
    @JsonIgnore private String todoTaskId;
    @JsonIgnore private String todoListId;
    @Valid @NotEmpty private String title;
    private String description;
    private Long dueDate;
    @Valid @NotNull private Priority priority;
    private Boolean isDone;
}
