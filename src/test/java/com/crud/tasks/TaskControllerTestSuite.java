package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskNotFoundException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import org.junit.Assert;
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

    @Test
    public void ShouldSaveToH2Database() throws TaskNotFoundException {
        //Given
        Task savedTask = new Task((long) 1, "testTitleH2Db", "dummyContent");
        taskService.saveTask(savedTask);

        //When
        Task recoveredTask = taskService.getTask((long) 1)
                .orElseThrow(TaskNotFoundException::new);
        //Then
        Assert.assertEquals("testTitleH2Db", recoveredTask.getTitle());
    }

    @Test
    public void ShouldGETTasks() {
        //Given
        //When
        //Then
    }

    @Test
    public void ShouldGETOneTask() {
        //Given
        //When
        //Then
    }

    @Test
    public void ShouldDELETETask() {
        //Given
        //When
        //Then
    }

    @Test
    public void ShouldPUTTask() {
        //Given
        //When
        //Then
    }

    @Test
    public void ShouldPOSTTask() {
        //Given
        //When
        //Then
    }
}