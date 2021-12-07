package com.sme.todo.model.primarykey;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTaskPKTest {

    @Test
    void testTodoTaskPK() {
        TodoTaskPK actual = new TodoTaskPK("todo-id1", "task-id1");
        TodoTaskPK expected = TodoTaskPK.builder().todoListId("todo-id1").todoTaskId("task-id1").build();
        TodoTaskPK not_expected = new TodoTaskPK();
        not_expected.setTodoListId("todo-id1");
        not_expected.setTodoTaskId("task-id2");

        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertNotEquals(not_expected, actual);
        assertNotEquals(not_expected.hashCode(), actual.hashCode());
    }

}