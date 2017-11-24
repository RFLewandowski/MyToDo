package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskNotFoundException;
import com.crud.tasks.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TaskServiceTestSuite {

    @Autowired
    private TaskService taskService;

    @Test
    public void ShouldSaveToH2Database() throws TaskNotFoundException {
        //Given
        //When
        Task recoveredTask = taskService.getTask((long) 1)
                .orElseThrow(TaskNotFoundException::new);
        //Then
        Assert.assertEquals("testTitleH2Db1", recoveredTask.getTitle());
    }

    @Before
    public void setUp() throws Exception {
        Task savedTask1 = new Task((long) 1, "testTitleH2Db1", "dummyContent1");
        taskService.saveTask(savedTask1);
    }
}
