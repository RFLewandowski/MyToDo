package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CoreConfiguration;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CoreConfiguration coreConfiguration;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void Should_SendInformationEmail() {
    //Given
    //
    //When
    emailScheduler.sendInformationEmail();

    //Then
        verify(taskRepository,times(1)).count();
        verify(adminConfig,times(1)).getAdminMail();
        verify(simpleEmailService,times(1)).send(any());
    }
}