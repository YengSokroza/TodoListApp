package com.example.todolistapp.service;

import com.example.todolistapp.model.Todo;
import com.example.todolistapp.repository.TodoListDataSource;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoListDataSource todoListDataSource;

    public TodoServiceImpl(TodoListDataSource todoListDataSource) {
        this.todoListDataSource = todoListDataSource;
    }

    @Override
    public List<Todo> getTodoList() {
        return  todoListDataSource.toDoListData();
    }

    public Todo getTodoListById(int id){
        Todo foundTodo = findTodoById(id);
        if(foundTodo != null){
            return foundTodo;
        }else{
            return null;
        }

    }

    @Override
    public Todo editTodo(int id, Todo updatedTodo) {
        Todo foundTodo = findTodoById(id);
        if(foundTodo != null){
            foundTodo.setTask(updatedTodo.getTask());
            foundTodo.setDone(updatedTodo.isDone());
            foundTodo.setDescription(updatedTodo.getDescription());
            return foundTodo;
        }else{
            return null;
        }
    }


    @Override
    public List<Todo> searchTodo(String task,Boolean isDone) {
        List<Todo> todoList = todoListDataSource.toDoListData();
        List<Todo> searchResults = new ArrayList<>();

        for (Todo todo : todoList) {
            boolean match = true;

            if (task != null && !task.isEmpty()) {
                if (!todo.getTask().contains(task)) {
                    match = false;
                }
            }
            if (isDone != null) {
                if (todo.isDone() != isDone) {
                    match = false;
                }
            }
            if (match) {
                searchResults.add(todo);
            }
        }

        return searchResults;
    }

    @Override
    public void deleteTodo(int id) {
        Todo foundTodo = findTodoById(id);
        List<Todo> todoList = todoListDataSource.toDoListData();
        todoList.remove(foundTodo);
    }

    @Override
    public Todo addTask(Todo todo) {
        List<Todo> todoList = todoListDataSource.toDoListData();
        if(!todo.getTask().isEmpty()){
            if(todoList.isEmpty()){
                todo.setId(todo.getId() + 1);
                todo.setCreateAt(formatNewDate());
                todoList.add(todo);
            }else{
                int lastIndex = todoList.size() - 1;
                if(lastIndex >= 0){
                    Todo lastTask = todoList.get(lastIndex);
                    todo.setId(lastTask.getId() + 1);
                    todo.setCreateAt(formatNewDate());
                    todoList.add(todo);
                }
            }
            return todo;
        }else{
            return null;
        }

    }



    //create new date with a format like : thurday 14, 2024
    public String formatNewDate(){
        Date date = new Date();
        DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
        return mediumFormat.format(date);
    }

    private Todo findTodoById(int id) {
        for (Todo todo: todoListDataSource.toDoListData()) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

     public boolean isAllSelected(String task, Boolean isDone) {
        return task == null && isDone == null;
    }





}
