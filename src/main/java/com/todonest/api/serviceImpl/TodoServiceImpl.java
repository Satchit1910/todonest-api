package com.todonest.api.serviceImpl;

import com.todonest.api.entity.Project;
import com.todonest.api.entity.Todo;
import com.todonest.api.exception.ResourceNotFoundException;
import com.todonest.api.repository.ProjectRepository;
import com.todonest.api.repository.TodoRepository;
import com.todonest.api.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void createTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setStatus("open");
        Todo newTodo = todoRepository.save(todo);
    }

    @Override
    public List<Todo> getTodosByProject(Long projectId) {
        Project connectedProject = projectRepository.findById(projectId).orElseThrow(
                () -> new ResourceNotFoundException("Project","Id",Long.toString(projectId))
        );
        return todoRepository.findByProject(connectedProject);
    }

    @Override
    public boolean updateTodo(Todo todo) {
        boolean isUpdated = false;
        if(todo!=null) {
            Todo oldTodo = todoRepository.findById(todo.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Todo","Id",Long.toString(todo.getId()))
            );
            oldTodo.setDescription(todo.getDescription());
            oldTodo.setStatus(todo.getStatus());
            oldTodo.setUpdatedAt(LocalDateTime.now());
            Todo updatedTodo = todoRepository.save(oldTodo);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo","Id",Long.toString(id))
        );
        todoRepository.deleteById(id);
        return true;
    }
}
