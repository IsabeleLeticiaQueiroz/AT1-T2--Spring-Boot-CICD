package com.example.todoapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// ADICIONE classes = TodoApiApplication.class
@SpringBootTest(classes = TodoApiApplication.class)
class TaskControllerTest {

    private TaskController taskController;

    @BeforeEach
    void setUp() {
        taskController = new TaskController();
    }

    @Test
    void testGetAllTasks_WhenNoTasks_ReturnsEmptyList() {
        List<Task> result = taskController.getAllTasks();
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateTask() {
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDescription("Test Description");
        
        Task result = taskController.createTask(newTask);
        
        assertNotNull(result.getId());
        assertEquals("Test Task", result.getTitle());
    }

    @Test
    void testGetTaskById() {
        Task newTask = new Task();
        newTask.setTitle("Find Me");
        Task created = taskController.createTask(newTask);
        
        Task found = taskController.getTaskById(created.getId());
        
        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void testGetTaskById_NotFound() {
        Task result = taskController.getTaskById(999L);
        assertNull(result);
    }
}