package com.example.todo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.models.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
