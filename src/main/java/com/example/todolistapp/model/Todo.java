package com.example.todolistapp.model;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private int id;
    private String task;
    private String description;
    private boolean isDone;
    private String createAt ;
}
