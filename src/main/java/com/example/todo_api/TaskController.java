package com.example.todo_api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;
    
    //1 todas as tarefas
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }
    
    //2 nova tarefa
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }
    
    // 3 tarefa por id
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}