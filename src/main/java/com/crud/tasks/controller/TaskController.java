package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(Long taskId) {
        return new TaskDto((long) 1, "test_title", "test_Content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTasks")
    public void deleteTask(Long TaskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTasks")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long) 1, "edited_test_title", "edited_test_Content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTasks")
    public void createTask(TaskDto taskDto) {
    }
}
