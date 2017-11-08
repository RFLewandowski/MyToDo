package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping(value = "getTask/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId));
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