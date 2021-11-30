package com.sme.todo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {

    @Test
    void testTodoList() {
        TodoList noArgConstructor = new TodoList();
        TodoList actual = TodoList.builder().todoListId("todo-id1").title("title1").build();
        TodoList expected = TodoList.builder().todoListId("todo-id1").title("title2").build();

        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
        assertNull(noArgConstructor.getTodoListId());
        assertNull(expected.getCreatedOn());
        assertNull(expected.getLastUpdatedOn());
    }

}