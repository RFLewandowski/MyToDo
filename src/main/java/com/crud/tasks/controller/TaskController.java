package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService dbService, TaskMapper taskMapper) {
        this.dbService = dbService;
        this.taskMapper = taskMapper;
    }

    @GetMapping()
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId));
    }

    @DeleteMapping(value = "{taskId}")
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