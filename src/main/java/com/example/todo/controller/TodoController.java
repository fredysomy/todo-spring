package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.todo.models.Todo;
import com.example.todo.service.TodoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Todo created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public ResponseEntity<Map<String, Object>> getMethodName(@PathVariable String id) {
        Long todoId = Long.parseLong(id);
        Todo todo = todoService.getTodoById(todoId);
        if (todo == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", todo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable String id) {
        Long todoId = Long.parseLong(id);
        Todo updatedTodo = todoService.updateTodoById(todoId, todo);
        if (updatedTodo == null) {
            return null;
        }
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMethodName(@PathVariable String id) {
        Long todoId = Long.parseLong(id);
        todoService.deleteTodoById(todoId);
        Map<String, Object> response = new HashMap<>();

        response.put("success", true);
        response.put("message", "Todo deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
