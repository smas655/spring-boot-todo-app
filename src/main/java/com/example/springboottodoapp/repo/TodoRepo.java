package com.example.springboottodoapp.repo;

import com.example.springboottodoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
}
