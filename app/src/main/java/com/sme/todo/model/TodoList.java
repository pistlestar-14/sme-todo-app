package com.sme.todo.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_list")
@DynamicUpdate
public class TodoList implements Serializable {

    @Id
    @Column(name = "todo_list_id", nullable = false, updatable = false, length = 36)
    private String todoListId;

    @Column(name = "title", nullable = false)
    @NotEmpty
    private String title;

    @Column(name = "created_on", nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @Column(name = "last_updated_on", nullable = false)
    private ZonedDateTime lastUpdatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TodoList)) return false;

        TodoList todoList = (TodoList) o;

        return new EqualsBuilder().append(getTodoListId(), todoList.getTodoListId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTodoListId())
                .toHashCode();
    }
}