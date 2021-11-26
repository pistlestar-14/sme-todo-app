package com.sme.todo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.dto.response.TodoTaskResponse;
import com.sme.todo.model.TodoTask;
import com.sme.todo.service.TodoTaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.sme.todo.util.MockDto.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoTaskControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private TodoTaskService todoTaskService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void getAllTodoTaskList() {
        try {
            TodoTask todoTask = prepareTodoTask();
            TodoTaskResponse todoTaskResponse = TodoTaskResponse.from(todoTask);
            given(todoTaskService.getAllTodoTaskList(TODO_LIST_ID, null))
                    .willReturn(Collections.singletonList(todoTaskResponse));

            MvcResult result = mockMvc
                    .perform(get("/api/v1/todo/" + TODO_LIST_ID + "/task"))
                    .andExpect(status().isOk())
                    .andReturn();

            Assertions.assertEquals(1, objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<List<TodoTaskResponse>>(){}).size());
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting all todo task", e);
        }
    }

    @Test
    void getTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            TodoTaskResponse todoTaskResponse = TodoTaskResponse.from(todoTask);
            given(todoTaskService.getTodoTaskById(TODO_LIST_ID, TODO_TASK_ID))
                    .willReturn(Optional.of(todoTaskResponse));

            MvcResult result = mockMvc
                    .perform(get("/api/v1/todo/" + TODO_LIST_ID + "/task/" + TODO_TASK_ID))
                    .andExpect(status().isOk())
                    .andReturn();

            Assertions.assertEquals(TODO_TASK_ID, objectMapper.readValue(
                    result.getResponse().getContentAsString(), TodoTaskResponse.class).getTodoTaskId());
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo task", e);
        }
    }

    @Test
    void createTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            TodoTaskCreateRequest todoTaskCreateRequest = prepareTodoTaskCreateRequest(todoTask);
            TodoTaskResponse todoTaskResponse = TodoTaskResponse.from(todoTask);
            given(todoTaskService.createTodoTaskById(todoTaskCreateRequest)).willReturn(Optional.of(todoTaskResponse));

            MvcResult result = mockMvc
                    .perform(post("/api/v1/todo/" + TODO_LIST_ID + "/task")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(todoTaskCreateRequest)))
                    .andExpect(status().isCreated())
                    .andReturn();

            Assertions.assertEquals(TODO_TASK_ID, objectMapper.readValue(
                    result.getResponse().getContentAsString(), TodoTaskResponse.class).getTodoTaskId());
        } catch (Exception e) {
            Assertions.fail("Error occurred while creating todo task", e);
        }
    }

    @Test
    void updateTodoTaskById() {
        try {
            TodoTask todoTask = prepareTodoTask();
            TodoTaskUpdateRequest todoTaskUpdateRequest = prepareTodoTaskUpdateRequest(todoTask);
            TodoTaskResponse todoTaskResponse = TodoTaskResponse.from(todoTask);
            given(todoTaskService.updateTodoTaskById(todoTaskUpdateRequest)).willReturn(Optional.of(todoTaskResponse));

            MvcResult result = mockMvc
                    .perform(put("/api/v1/todo/" + TODO_LIST_ID + "/task/" + TODO_TASK_ID)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(todoTaskUpdateRequest)))
                    .andExpect(status().isOk())
                    .andReturn();

            Assertions.assertEquals(TODO_TASK_ID, objectMapper.readValue(
                    result.getResponse().getContentAsString(), TodoTaskResponse.class).getTodoTaskId());
        } catch (Exception e) {
            Assertions.fail("Error occurred while updating todo task", e);
        }
    }

    @Test
    void deleteTodoTaskById_found() {
        try {
            given(todoTaskService.deleteTodoTaskById(TODO_LIST_ID, TODO_TASK_ID)).willReturn(true);

            mockMvc
                    .perform(delete("/api/v1/todo/" + TODO_LIST_ID + "/task/" + TODO_TASK_ID))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting todo task", e);
        }
    }

    @Test
    void deleteTodoTaskById_not_found() {
        try {
            given(todoTaskService.deleteTodoTaskById(TODO_LIST_ID, TODO_TASK_ID)).willReturn(false);

            mockMvc
                    .perform(delete("/api/v1/todo/" + TODO_LIST_ID + "/task/" + TODO_TASK_ID))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting todo task", e);
        }
    }
}