package com.sme.todo.model.primaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoTaskPK implements Serializable {
    private String todoListId;
    private String todoTaskId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TodoTaskPK)) return false;

        TodoTaskPK that = (TodoTaskPK) o;

        return new EqualsBuilder()
                .append(getTodoListId(), that.getTodoListId())
                .append(getTodoTaskId(), that.getTodoTaskId())
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