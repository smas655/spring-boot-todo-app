package com.example.springboottodoapp.services;

import com.example.springboottodoapp.entity.Todo;
import com.example.springboottodoapp.repo.TodoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service @Transactional
public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public Iterable<Todo> getAll(){
        return todoRepo.findAll();
    }

    public Optional<Todo> getById(Long id){
        return todoRepo.findById(id);
    }

    public Todo save(Todo todo){
        if (todo.getId() == null){
            todo.setCreatedAt(Instant.now());
        }

        todo.setUpdatedAt(Instant.now());
        return todoRepo.save(todo);
    }

    public void delete(Todo todo){
       todoRepo.delete(todo);
    }
}
