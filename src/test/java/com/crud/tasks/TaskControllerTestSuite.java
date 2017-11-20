package com.crud.tasks;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskNotFoundException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskController taskController;


    @Test
    public void ShouldLoadContext() {
        Assert.assertNotNull(taskController);
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
    public void ShouldGETTasks() throws Exception {
        //Given
        Task savedTask = new Task((long) 1, "testTitleH2Db", "dummyContent");
        taskService.saveTask(savedTask);
        //When
        //Then
        this.mockMvc
                .perform(get("/v1/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].title").value("testTitleH2Db"));


//        System.out.println(this.mockMvc
//                .perform(get("/v1/tasks"))
//                .andReturn().getResponse().getContentAsString());


//                .andExpect(jsonPath("$.title").value("testTitleH2Db"));

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
    public void ShouldPOSTTask() throws Exception {
        //Given
        //When
        //Then
    }
}