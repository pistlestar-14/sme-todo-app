package com.sme.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class TodoListCreateRequest implements Serializable {
    @NotEmpty private String title;
}
