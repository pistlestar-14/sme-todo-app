package com.sme.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoListControllerTest {

    @Autowired private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void initAll() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Test
    void getAllTodoList() {
    }

    @Test
    void getTodoListById() {
    }

    @Test
    void createTodoListById() {
    }

    @Test
    void updateTodoListById() {
    }

    @Test
    void deleteTodoListById() {
    }
}