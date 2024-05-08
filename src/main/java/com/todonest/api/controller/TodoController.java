package com.todonest.api.controller;

import com.todonest.api.constants.TodoConstants;
import com.todonest.api.dto.ResponseDto;
import com.todonest.api.entity.Todo;
import com.todonest.api.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/todo",produces = {"application/json"})
@CrossOrigin(origins = "*") // Allow requests from any origin
public class TodoController {

    @Autowired
    private ITodoService iTodoService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTodo(@RequestBody Todo todo) {
        iTodoService.createTodo(todo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(TodoConstants.STATUS_201,TodoConstants.MESSAGE_201));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Todo>> getTodosByProjectId(@PathVariable Long projectId) {
        List<Todo> todos = iTodoService.getTodosByProject(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateTodo(@RequestBody Todo todo) {
        boolean isUpdated = iTodoService.updateTodo(todo);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(TodoConstants.STATUS_200,TodoConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(TodoConstants.STATUS_417,TodoConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteTodo(@PathVariable Long id) {
        boolean isDeleted = iTodoService.deleteTodo(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(TodoConstants.STATUS_200,TodoConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(TodoConstants.STATUS_417,TodoConstants.MESSAGE_417_DELETE));
        }
    }
}
