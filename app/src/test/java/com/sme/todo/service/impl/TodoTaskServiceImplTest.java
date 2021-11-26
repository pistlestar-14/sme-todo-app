package com.sme.todo.service.impl;

import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.model.TodoTask;
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

class TodoTaskServiceImplTest {

    @InjectMocks private TodoTaskServiceImpl todoTaskService;
    @Mock private TodoTaskRepository todoTaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTodoTaskList_with_isDone_null() {
        try {
            TodoTask todoTask = prepareTodoTask();
            given(todoTaskRepository.findByTodoListIdOrderByPriorityDesc(TODO_LIST_ID)).willReturn(Collections.singletonList(todoTask));

            Assertions.assertFalse(CollectionUtils.isEmpty(todoTaskService.getAllTodoTaskList(TODO_LIST_ID, null)));
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo task list with isDone null", e);
        }
    }

    @Test
    void getAllTodoTaskList_with_isDone_not_null() {
        try {
            TodoTask todoTask = prepareTodoTask();
            given(todoTaskRepository.findByTodoListIdAndIsDoneOrderByPriorityDesc(TODO_LIST_ID, false)).willReturn(Collections.singletonList(todoTask));

            Assertions.assertFalse(CollectionUtils.isEmpty(todoTaskService.getAllTodoTaskList(TODO_LIST_ID, false)));
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo task list with isDone not null", e);
        }
    }

    @Test
    void getTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            given(todoTaskRepository.findByTodoListIdAndTodoTaskId(TODO_LIST_ID, TODO_TASK_ID)).willReturn(Optional.of(todoTask));
            Assertions.assertNotNull(todoTaskService.getTodoTaskById(TODO_LIST_ID, TODO_TASK_ID));
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo task", e);
        }
    }

    @Test
    void createTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            given(todoTaskRepository.save(any())).willReturn(todoTask);

            TodoTaskCreateRequest todoTaskCreateRequest = prepareTodoTaskCreateRequest(todoTask);
            Assertions.assertNotNull(todoTaskService.createTodoTaskById(todoTaskCreateRequest));
        } catch (Exception e) {
            Assertions.fail("Error occurred while creating todo task", e);
        }
    }

    @Test
    void updateTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            given(todoTaskRepository.findByTodoListIdAndTodoTaskId(TODO_LIST_ID, TODO_TASK_ID)).willReturn(Optional.of(todoTask));
            given(todoTaskRepository.save(any())).willReturn(todoTask);

            TodoTaskUpdateRequest todoTaskUpdateRequest = prepareTodoTaskUpdateRequest(todoTask);
            Assertions.assertNotNull(todoTaskService.updateTodoTaskById(todoTaskUpdateRequest));
        } catch (Exception e) {
            Assertions.fail("Error occurred while updating todo task", e);
        }
    }

    @Test
    void deleteTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            given(todoTaskRepository.findByTodoListIdAndTodoTaskId(TODO_LIST_ID, TODO_TASK_ID)).willReturn(Optional.of(todoTask));
            doNothing().when(todoTaskRepository).delete(any());

            Assertions.assertTrue(todoTaskService.deleteTodoTaskById(TODO_LIST_ID, TODO_TASK_ID));
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting todo task", e);
        }
    }
}