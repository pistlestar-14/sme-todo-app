package com.sme.todo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.response.TodoListResponse;
import com.sme.todo.model.TodoList;
import com.sme.todo.service.TodoListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
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
class TodoListControllerTest {

    private final TodoList todoList = prepareTodoList();
    private final TodoListResponse todoListResponse = TodoListResponse.from(todoList);
    private final TodoListCreateRequest todoListCreateRequest = prepareTodoListCreateRequest(todoList);
    private final TodoListUpdateRequest todoListUpdateRequest = prepareTodoListUpdateRequest(todoList);

    @Autowired private MockMvc mockMvc;
    @MockBean private TodoListService todoListService;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        given(todoListService.getAllTodoList()).willReturn(Collections.singletonList(todoListResponse));
        given(todoListService.getTodoListById(TODO_LIST_ID)).willReturn(Optional.of(todoListResponse));
        given(todoListService.createTodoListById(todoListCreateRequest)).willReturn(Optional.of(todoListResponse));
        given(todoListService.updateTodoListById(todoListUpdateRequest)).willReturn(Optional.of(todoListResponse));
        given(todoListService.deleteTodoListById(TODO_LIST_ID)).willReturn(true);
        given(todoListService.deleteTodoListById(TODO_LIST_INVALID_ID)).willReturn(false);
    }

    @Test
    void getAllTodoList() {
        try {
            MvcResult result = mockMvc
                    .perform(get("/api/v1/todo"))
                    .andExpect(status().isOk())
                    .andReturn();

            Assertions.assertEquals(1, objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<List<TodoListResponse>>(){}).size());
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting all todo list", e);
        }
    }

    @Test
    void getTodoListById() {
        try {
            MvcResult result = mockMvc
                    .perform(get("/api/v1/todo/" + TODO_LIST_ID))
                    .andExpect(status().isOk())
                    .andReturn();

            Assertions.assertEquals(TODO_LIST_ID, objectMapper.readValue(
                    result.getResponse().getContentAsString(), TodoListResponse.class).getTodoListId());
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting todo list", e);
        }
    }

    @Test
    void createTodoListById() {
        try {
            MvcResult result = mockMvc
                    .perform(post("/api/v1/todo")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(todoListCreateRequest)))
                    .andExpect(status().isCreated())
                    .andReturn();

            Assertions.assertEquals(TODO_LIST_ID, objectMapper.readValue(
                    result.getResponse().getContentAsString(), TodoListResponse.class).getTodoListId());
        } catch (Exception e) {
            Assertions.fail("Error occurred while creating todo list", e);
        }
    }

    @Test
    void updateTodoListById() {
        try {
            MvcResult result = mockMvc
                    .perform(put("/api/v1/todo/" + TODO_LIST_ID)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(todoListUpdateRequest)))
                    .andExpect(status().isOk())
                    .andReturn();

            Assertions.assertEquals(TODO_LIST_ID, objectMapper.readValue(
                    result.getResponse().getContentAsString(), TodoListResponse.class).getTodoListId());
        } catch (Exception e) {
            Assertions.fail("Error occurred while updating todo list", e);
        }
    }

    @Test
    void deleteTodoListById_found() {
        try {
            mockMvc
                    .perform(delete("/api/v1/todo/" + TODO_LIST_ID))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting todo list", e);
        }
    }

    @Test
    void deleteTodoListById_not_found() {
        try {
            mockMvc
                    .perform(delete("/api/v1/todo/" + TODO_LIST_INVALID_ID))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting todo list", e);
        }
    }
}