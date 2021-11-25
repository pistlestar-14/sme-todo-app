package com.sme.todo.dto.request;

import com.sme.todo.constant.Priority;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class TodoTaskCreateRequest implements Serializable {
    private String todoListId;
    @NotEmpty private String title;
    private String description;
    private Long dueDate;
    @NotNull private Priority priority;
}
