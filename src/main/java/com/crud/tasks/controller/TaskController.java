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
    public TaskDto getTask(@PathVariable("taskId") Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        dbService.deleteTask(taskId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    }
}