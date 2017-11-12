package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TaskNotFoundException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping()
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(taskService.getAllTasks());
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) throws TaskNotFoundException {
        return taskMapper
                .mapToTaskDto(taskService
                        .getTask(taskId)
                        .orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(taskService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        taskService.saveTask(taskMapper.mapToTask(taskDto));
    }
}