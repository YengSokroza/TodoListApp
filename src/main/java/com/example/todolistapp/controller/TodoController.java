package com.example.todolistapp.controller;

import com.example.todolistapp.model.Todo;
import com.example.todolistapp.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;

    }

    @GetMapping
    public String todoList(Model model){
        List<Todo> todos = todoService.getTodoList();
        model.addAttribute("todoList",todos);
        return "index";
    }

    @GetMapping("/new")
    public String showAddStudentForm(Model model){
        model.addAttribute("new",new Todo());
        return "add_task";
    }

    @PostMapping("/new")
    public String addNewTask(Todo todo){
        todoService.addTask(todo);
        return "redirect:/todos";
    }

    @GetMapping("/{id}")
    public String getTodo(@PathVariable int id, Model model){
        boolean disableCheckbox = true;
        Todo todo = todoService.getTodoListById(id);
        if(todo != null){
            model.addAttribute("todo",todo);
            model.addAttribute("disableCheckbox",disableCheckbox);
            return "todo_detail";
        }else{
            return  "todo_notFound";
        }
    }

    @GetMapping("/edit/{id}")
    public String ShowEditTodoForm(@PathVariable int id, Model model){
        Todo todo = todoService.getTodoListById(id);
        model.addAttribute("todo",todo);
        return "edit_task";
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable int id,Todo todo) {
        if(todo != null){
            todoService.editTodo(id, todo);
            return "redirect:/todos";
        }else{
            return "todo_notFound";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }



    @GetMapping("/search")
    public String searchTodo(
            @RequestParam(value = "task", required = false) String task,
            @RequestParam(value = "isDone", required = false) Boolean isDone,
            Model model
    ) {
        List<Todo> todos = todoService.searchTodo(task,isDone);
        if(!todos.isEmpty()){
            model.addAttribute("todoList",todos);
            return "index";
        }else{
            return "todo_notFound";
        }
    }


}
