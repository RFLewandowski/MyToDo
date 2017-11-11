package com.crud.tasks;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTestSuite {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;




    @Test
    public void ShouldLoadContext() {
    }
}