package com.sme.todo.model;

import com.sme.todo.model.primaryKey.TodoTaskPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(TodoTaskPK.class)
@Table(name = "todo_task")
@DynamicUpdate
public class TodoTask implements Serializable {

    @Id
    @Column(name = "todo_list_id", nullable = false, updatable = false, length = 36)
    private String todoListId;

    @Id
    @Column(name = "todo_task_id", nullable = false, updatable = false, length = 36)
    private String todoTaskId;

    @Column(name = "title", nullable = false)
    @NotEmpty
    private String title;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @Column(name = "due_date")
    private ZonedDateTime dueDate;

    @Column(name = "created_on", nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @Column(name = "last_updated_on", nullable = false)
    private ZonedDateTime lastUpdatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TodoTask)) return false;

        TodoTask todoTask = (TodoTask) o;

        return new EqualsBuilder()
                .append(getTodoListId(), todoTask.getTodoListId())
                .append(getTodoTaskId(), todoTask.getTodoTaskId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTodoListId())
                .append(getTodoTaskId())
                .toHashCode();
    }
}