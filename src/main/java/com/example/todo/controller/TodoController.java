package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.todo.models.Todo;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public String createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
        return "Todo created successfully";
    }

    @GetMapping("/all")
    public List<Todo> getMethodName() {
        try {
            return todoService.getTodos();
        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Todo> getMethodName(@PathVariable String id) {
        Long todoId = Long.parseLong(id);
        Todo todo = todoService.getTodoById(todoId);
        if (todo == null) {
            return null;
        }
        return ResponseEntity.ok(todo);
    }

}
