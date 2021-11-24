package com.sme.todo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class TodoTaskCreateRequest implements Serializable {
    private String todoListId;
    @NotEmpty private String title;
    private String description;
    private Long dueDate;
    @NotNull private Integer priority;
}
