package com.example.todolistapp.service;

import com.example.todolistapp.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodoList();
    Todo addTask(Todo todo);
    Todo getTodoListById(int id);
    Todo editTodo(int id,Todo updatedTodo);
    List<Todo> searchTodo(String task, Boolean isDone);
    void deleteTodo(int id);
    boolean isAllSelected(String task, Boolean isDone);

}
