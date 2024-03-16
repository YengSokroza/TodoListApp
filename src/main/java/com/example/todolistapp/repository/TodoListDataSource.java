package com.example.todolistapp.repository;

import com.example.todolistapp.model.Todo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class  TodoListDataSource {
    Date date = new Date();
    DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    private static List<Todo> todoList = new ArrayList<>();
    public List<Todo> toDoListData(){
        return todoList;
    }
    TodoListDataSource(){
        todoList.add(new Todo(1,"Buy dog food","Buy at Lucky mart around 5:00pm",false,mediumFormat.format(date)));
        todoList.add(new Todo(2,"Water plant"," at the garden backyard",true,mediumFormat.format(date)));
        todoList.add(new Todo(3,"Wash dishes","After school",false,mediumFormat.format(date)));
    }
}
