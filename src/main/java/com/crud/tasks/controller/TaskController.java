package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getTask/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return new TaskDto((long) 1, "test_title", "test_Content");
    }

    @DeleteMapping(value = "deleteTask/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "updateTasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return new TaskDto((long) 1, "edited_test_title", "edited_test_Content");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "createTasks")
    public void createTask(@RequestBody TaskDto taskDto) {
    }
}