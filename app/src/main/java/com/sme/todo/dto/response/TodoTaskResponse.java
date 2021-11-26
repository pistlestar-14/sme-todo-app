package com.sme.todo.dto.response;

import com.sme.todo.constant.Priority;
import com.sme.todo.model.TodoTask;
import com.sme.todo.util.DateUtil;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Value(staticConstructor = "of")
public class TodoTaskResponse implements Serializable {
    @NotEmpty String todoTaskId;
    @NotEmpty String title;
    String description;
    Long dueDate;
    @NotNull Priority priority;
    @NotNull Boolean isDone;

    public static TodoTaskResponse from(TodoTask todoTask) {
        return TodoTaskResponse.of(
                todoTask.getTodoTaskId(),
                todoTask.getTitle(),
                todoTask.getDescription(),
                DateUtil.toEpochMilli(todoTask.getDueDate()),
                Priority.fromValue(todoTask.getPriority()),
                todoTask.getIsDone());
    }
}
