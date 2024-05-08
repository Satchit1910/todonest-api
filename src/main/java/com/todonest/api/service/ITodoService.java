package com.todonest.api.service;

import com.todonest.api.entity.Project;
import com.todonest.api.entity.Todo;

import java.util.List;

public interface ITodoService {

    void createTodo(Todo todo);

    List<Todo> getTodosByProject(Long projectId);

    boolean updateTodo(Todo todo);

    boolean deleteTodo(Long id);
}
