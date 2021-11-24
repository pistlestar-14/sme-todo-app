package com.sme.todo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class TodoListCreateRequest implements Serializable {
    @NotEmpty private String title;
}
