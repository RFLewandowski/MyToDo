package com.crud.tasks;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.task.Task;
import com.crud.tasks.domain.task.TaskDto;
import com.crud.tasks.domain.task.TaskNotFoundException;
import com.crud.tasks.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("classpath:test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskController taskController;

    @Autowired
    private JsonConverter jsonConverter; //correcly autowired despite Intelij showing otherwise


    @Before
    public void setUp() throws Exception {
        Task savedTask1 = new Task((long) 1, "testTitleH2Db1", "dummyContent1");
        Task savedTask2 = new Task((long) 2, "testTitleH2Db2", "dummyContent2");
        Task savedTask3 = new Task((long) 3, "testTitleH2Db3", "dummyContent3");
        taskService.saveTask(savedTask1);
        taskService.saveTask(savedTask2);
        taskService.saveTask(savedTask3);
    }


    @Test
    public void ShouldLoadContext() {
        assertNotNull(taskController);
    }

    @Test
    public void ShouldGETTasks() throws Exception {
        this.mockMvc
                .perform(get("/v1/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].title").value("testTitleH2Db1"));
    }

    @Test
    public void ShouldGETOneTask() throws Exception {
        this.mockMvc
                .perform(get("/v1/tasks/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title").value("testTitleH2Db2"));
    }

    @Test
    public void ShouldDELETETask() throws Exception {

        //Given
        //When
        this.mockMvc
                .perform(delete("/v1/tasks/3"))
                .andDo(print())
                .andExpect(status().isOk());

        List<Task> recoveredTasks = taskService.getAllTasks();

        //Then
        Assert.assertEquals(2, recoveredTasks.size());

    }

    @Test
    public void ShouldPUTTask() throws Exception {
        //Given
        TaskDto dummyTask = new TaskDto((long) 3, "dummyTitle", "dummyContent");
        String dummyTaskJason = jsonConverter.json(dummyTask);

        //When
        this.mockMvc
                .perform(put("/v1/tasks/3")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(dummyTaskJason))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title").value("dummyTitle"));
    }

    @Test
    public void ShouldPOSTTask() throws Exception {
        //Given
        TaskDto dummyTask = new TaskDto((long) 4, "dummyTitle", "dummyContent");
        String dummyTaskJason = jsonConverter.json(dummyTask);

        //When
        this.mockMvc
                .perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(dummyTaskJason))
                .andDo(print())
                .andExpect(status().isOk());

        Task recoveredTask = taskService.getTask((long) 4)
                .orElseThrow(TaskNotFoundException::new);
        //Then
        Assert.assertEquals("dummyTitle", recoveredTask.getTitle());
    }
}