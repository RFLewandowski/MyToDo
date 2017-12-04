package com.crud.tasks.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}