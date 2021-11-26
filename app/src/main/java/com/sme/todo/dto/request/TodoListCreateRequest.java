package com.sme.todo.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@Data
public class TodoListCreateRequest implements Serializable {
    @NotEmpty private String title;
}
