package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.models.Todo;
import com.example.todo.repository.TodoRepository;
import java.util.List;

@Service
public class TodoService {
    @Autowired

    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodoById(Long id, Todo todo) {
        Todo todoToUpdate = todoRepository.findById(id).orElse(null);
        if (todoToUpdate == null) {
            return null;
        }
        else {
            todoToUpdate.setTitle(todo.getTitle() != null ? todo.getTitle() : todoToUpdate.getTitle());
            todoToUpdate.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToUpdate.getDescription());
            todoToUpdate.setCompleted(todo.isCompleted());
            return todoRepository.save(todoToUpdate);
        }
    }

}
