package com.sme.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sme.todo.constant.Priority;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
public class TodoTaskUpdateRequest implements Serializable {
    @JsonIgnore @NotEmpty private String todoTaskId;
    @JsonIgnore @NotEmpty private String todoListId;
    @NotEmpty private String title;
    private String description;
    private Long dueDate;
    @NotNull private Priority priority;
    private Boolean isDone;
}
