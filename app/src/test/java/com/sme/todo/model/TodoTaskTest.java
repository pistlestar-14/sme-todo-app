package com.sme.todo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTaskTest {

    @Test
    void testTodoTask() {
        TodoTask noArgConstructor = new TodoTask();
        TodoTask actual = TodoTask.builder().todoListId("todo-id1").todoTaskId("task-id1").title("title1").build();
        TodoTask expected = TodoTask.builder().todoListId("todo-id1").todoTaskId("task-id1").title("title2").build();

        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertNull(noArgConstructor.getTodoTaskId());
        assertNull(expected.getCreatedOn());
        assertNull(expected.getLastUpdatedOn());
    }

}