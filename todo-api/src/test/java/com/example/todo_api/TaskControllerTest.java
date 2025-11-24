package com.example.todoapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskControllerTest {

    private TaskController taskController;

    @BeforeEach
    void setUp() {
        taskController = new TaskController();
    }

    // teste da rota 1
    @Test
    void testGetAllTasks_WhenNoTasks_ReturnsEmptyList() {
        List<Task> result = taskController.getAllTasks();
        assertTrue(result.isEmpty());
    }

    // teste da rota 2
    @Test
    void testCreateTask_ValidTask_ReturnsCreatedTask() {
        Task newTask = new Task();
        newTask.setTitle("Estudar Spring Boot");
        newTask.setDescription("Aprender testes unitários");
        
        Task result = taskController.createTask(newTask);
        
        assertNotNull(result.getId());
        assertEquals("Estudar Spring Boot", result.getTitle());
        assertEquals("Aprender testes unitários", result.getDescription());
        assertFalse(result.isCompleted());
    }

    // teste da rota 3
    @Test
    void testGetTaskById_WhenTaskExists_ReturnsTask() {
        Task newTask = new Task();
        newTask.setTitle("Tarefa de teste");
        Task created = taskController.createTask(newTask);
        
        Task result = taskController.getTaskById(created.getId());
        
        assertNotNull(result);
        assertEquals(created.getId(), result.getId());
        assertEquals("Tarefa de teste", result.getTitle());
    }

    @Test
    void testGetTaskById_WhenTaskNotExists_ReturnsNull() {
        Task result = taskController.getTaskById(999L);
        assertNull(result);
    }

    // verifica se lista esta sendo preenchida
    @Test
    void testCreateTask_ThenGetAll_ReturnsListWithTask() {
        Task newTask = new Task();
        newTask.setTitle("Nova tarefa");
        taskController.createTask(newTask);
        
        List<Task> result = taskController.getAllTasks();
        
        assertEquals(1, result.size());
        assertEquals("Nova tarefa", result.get(0).getTitle());
    }
}