package com.sme.todo.service.impl;

import com.sme.todo.dto.request.TodoTaskCreateRequest;
import com.sme.todo.dto.request.TodoTaskUpdateRequest;
import com.sme.todo.dto.response.TodoTaskResponse;
import com.sme.todo.model.TodoTask;
import com.sme.todo.repository.TodoTaskRepository;
import com.sme.todo.service.TodoTaskService;
import com.sme.todo.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoTaskServiceImpl implements TodoTaskService {

    private final TodoTaskRepository todoTaskRepository;

    @Autowired
    public TodoTaskServiceImpl(TodoTaskRepository todoTaskRepository) {
        this.todoTaskRepository = todoTaskRepository;
    }

    @Override
    public List<TodoTaskResponse> getAllTodoTaskList(String todoListId, Boolean isDone) {
        if (Objects.isNull(isDone)) {
            return todoTaskRepository.findByTodoListIdOrderByPriorityDesc(todoListId)
                    .stream()
                    .map(TodoTaskResponse::from)
                    .collect(Collectors.toList());
        } else {
            return todoTaskRepository.findByTodoListIdAndIsDoneOrderByPriorityDesc(todoListId, isDone)
                    .stream()
                    .map(TodoTaskResponse::from)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Optional<TodoTaskResponse> getTodoTaskById(String todoListId, String todoTaskId) {
        return todoTaskRepository.findByTodoListIdAndTodoTaskId(todoListId, todoTaskId)
                .map(TodoTaskResponse::from);
    }

    @Override
    public Optional<TodoTaskResponse> createTodoTaskById(TodoTaskCreateRequest todoTaskCreateRequest) {
        TodoTask todoTask = TodoTask.builder()
                .todoListId(todoTaskCreateRequest.getTodoListId())
                .todoTaskId(DateUtil.getUniqueTimeBasedUUID())
                .title(todoTaskCreateRequest.getTitle())
                .description(todoTaskCreateRequest.getDescription())
                .dueDate(DateUtil.fromEpochMilli(todoTaskCreateRequest.getDueDate()))
                .priority(todoTaskCreateRequest.getPriority())
                .createdOn(DateUtil.timeNow())
                .lastUpdatedOn(DateUtil.timeNow())
                .isDone(false)
                .build();

        return Optional.ofNullable(TodoTaskResponse.from(todoTaskRepository.save(todoTask)));
    }

    @Transactional
    @Override
    public Optional<TodoTaskResponse> updateTodoTaskById(TodoTaskUpdateRequest todoTaskUpdateRequest) {
        return todoTaskRepository
                .findByTodoListIdAndTodoTaskId(todoTaskUpdateRequest.getTodoListId(),todoTaskUpdateRequest.getTodoTaskId())
                .map(todoTask -> {
                    todoTask.setTitle(todoTaskUpdateRequest.getTitle());
                    todoTask.setDescription(todoTaskUpdateRequest.getDescription());
                    todoTask.setDueDate(DateUtil.fromEpochMilli(todoTaskUpdateRequest.getDueDate()));
                    todoTask.setPriority(todoTaskUpdateRequest.getPriority());
                    todoTask.setIsDone(todoTaskUpdateRequest.getIsDone());
                    todoTask.setLastUpdatedOn(DateUtil.timeNow());
                    return TodoTaskResponse.from(todoTaskRepository.save(todoTask));
                });
    }

    @Transactional
    @Override
    public void deleteTodoTaskById(String todoListId, String todoTaskId) {
        todoTaskRepository.findByTodoListIdAndTodoTaskId(todoListId, todoTaskId)
                .ifPresent(todoTaskRepository::delete);
    }
}
