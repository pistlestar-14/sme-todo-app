package com.sme.todo.service.impl;

import com.sme.todo.dto.request.TodoListCreateRequest;
import com.sme.todo.dto.request.TodoListUpdateRequest;
import com.sme.todo.dto.response.TodoListResponse;
import com.sme.todo.model.TodoList;
import com.sme.todo.repository.TodoListRepository;
import com.sme.todo.repository.TodoTaskRepository;
import com.sme.todo.service.TodoListService;
import com.sme.todo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final TodoTaskRepository todoTaskRepository;

    @Autowired
    public TodoListServiceImpl(TodoListRepository todoListRepository, TodoTaskRepository todoTaskRepository) {
        this.todoListRepository = todoListRepository;
        this.todoTaskRepository = todoTaskRepository;
    }

    @Override
    public List<TodoListResponse> getAllTodoList() {
        return todoListRepository.findAllByOrderByCreatedOnAsc()
                .stream()
                .filter(Objects::nonNull)
                .map(TodoListResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoListResponse> getTodoListById(String todoListId) {
        return todoListRepository.findById(todoListId).map(TodoListResponse::from);
    }

    @Override
    public Optional<TodoListResponse> createTodoListById(TodoListCreateRequest todoListCreateRequest) {
        TodoList todoList = TodoList.builder()
                .todoListId(DateUtil.getUniqueTimeBasedUUID())
                .title(todoListCreateRequest.getTitle())
                .createdOn(DateUtil.timeNow())
                .lastUpdatedOn(DateUtil.timeNow())
                .build();

        return Optional.ofNullable(TodoListResponse.from(todoListRepository.save(todoList)));
    }

    @Transactional
    @Override
    public Optional<TodoListResponse> updateTodoListById(TodoListUpdateRequest todoListUpdateRequest) {
        return todoListRepository.findById(todoListUpdateRequest.getTodoListId())
                .map(todoList -> {
                    todoList.setTitle(todoListUpdateRequest.getTitle());
                    todoList.setLastUpdatedOn(DateUtil.timeNow());
                    return TodoListResponse.from(todoListRepository.save(todoList));
                });
    }

    @Transactional
    @Override
    public Boolean deleteTodoListById(String todoListId) {
        return todoListRepository.findById(todoListId)
                .map(todoList -> {
                    todoListRepository.delete(todoList);
                    todoTaskRepository.deleteByTodoListId(todoListId);
                    return true;
                })
                .orElse(false);
    }
}
