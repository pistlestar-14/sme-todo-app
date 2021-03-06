package com.sme.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class TodoListUpdateRequest implements Serializable {
    @JsonIgnore private String todoListId;
    @NotEmpty private String title;
}
