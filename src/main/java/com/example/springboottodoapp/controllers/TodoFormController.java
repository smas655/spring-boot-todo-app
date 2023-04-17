package com.example.springboottodoapp.controllers;

import com.example.springboottodoapp.entity.Todo;
import com.example.springboottodoapp.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoFormController {

    private final TodoService todoService;

    public TodoFormController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/create-todo")
    public String showCreateForm(Todo todo){
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem (@Valid Todo todo, BindingResult result, Model model){

        Todo item = new Todo();
        item.setDescription(todo.getDescription());
        item.setIsComplete(todo.getIsComplete());
        todoService.save(todo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id, Model model){
        Todo todo = todoService.getById(id).orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + "not found"));
        todoService.delete(todo);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Todo todo = todoService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + "not found"));

        model.addAttribute("todo", todo);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid Todo todo, BindingResult result, Model model){
        Todo item = todoService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + "not found"));
        item.setIsComplete(todo.getIsComplete());
        item.setDescription(todo.getDescription());

        todoService.save(item);
        return "redirect:/";
    }
}
