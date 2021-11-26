package com.sme.todo.service.impl;

import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.model.TodoList;
import com.sme.todo.repository.TodoListRepository;
import com.sme.todo.repository.TodoTaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Optional;

import static com.sme.todo.util.MockDto.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

class TodoListServiceImplTest {


    @InjectMocks private TodoListServiceImpl todoListService;
    @Mock private TodoListRepository todoListRepository;
    @Mock private TodoTaskRepository todoTaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTodoList() {
        try {
            TodoList todoList = prepareTodoList();
            given(todoListRepository.findAllByOrderByCreatedOnAsc()).willReturn(Collections.singletonList(todoList));
            Assertions.assertFalse(CollectionUtils.isEmpty(todoListService.getAllTodoList()));
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo list", e);
        }
    }

    @Test
    void getTodoListById() {
        try {
            TodoList todoList = prepareTodoList();
            given(todoListRepository.findById(TODO_LIST_ID)).willReturn(Optional.of(todoList));
            Assertions.assertNotNull(todoListService.getTodoListById(TODO_LIST_ID));
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo list", e);
        }
    }

    @Test
    void createTodoListById() {
        try {
            TodoList todoList = prepareTodoList();
            given(todoListRepository.save(any())).willReturn(todoList);

            TodoListCreateRequest todoListCreateRequest = prepareTodoListCreateRequest(todoList);
            Assertions.assertNotNull(todoListService.createTodoListById(todoListCreateRequest));
        } catch (Exception e) {
            Assertions.fail("Error occurred while creating todo list", e);
        }
    }

    @Test
    void updateTodoListById() {
        try {
            TodoList todoList = prepareTodoList();
            given(todoListRepository.findById(TODO_LIST_ID)).willReturn(Optional.of(todoList));
            given(todoListRepository.save(any())).willReturn(todoList);

            TodoListUpdateRequest todoListUpdateRequest = prepareTodoListUpdateRequest(todoList);
            Assertions.assertNotNull(todoListService.updateTodoListById(todoListUpdateRequest));
        } catch (Exception e) {
            Assertions.fail("Error occurred while updating todo list", e);
        }
    }

    @Test
    void deleteTodoListById() {
        try {
            TodoList todoList = prepareTodoList();
            given(todoListRepository.findById(TODO_LIST_ID)).willReturn(Optional.of(todoList));
            doNothing().when(todoListRepository).delete(any());
            doNothing().when(todoTaskRepository).deleteByTodoListId(any());

            Assertions.assertTrue(todoListService.deleteTodoListById(TODO_LIST_ID));
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting todo list", e);
        }
    }

}