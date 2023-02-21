package com.engeto.Lekce11DU1;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TodoItemController {

    TodoItemService todoItemService;

    public TodoItemController() throws SQLException {
        todoItemService = new TodoItemService();
    }

    @GetMapping("/todoItems")
    public List<TodoItem> getAllTodoItems() throws SQLException {
        return TodoItemService.getAllTodoItems();
    }

    @GetMapping("/todoItem/{id}")
    public TodoItem getTodoItem(@PathVariable("id") Long id) throws SQLException {
        return todoItemService.getTodoItem(id);
    }

    @PostMapping("/todoItem")
    public void saveTodoItem(@RequestBody TodoItem todoItem) throws SQLException {
        todoItemService.saveTodoItem(todoItem);
    }

    @PutMapping("/todoItem/{id}")
    public void setTodoItemAsDone(@PathVariable("id") Long id) throws SQLException {
        todoItemService.setTodoItemAsDone(id);
    }

    @PutMapping("/todoItem1")
    public void updateTodoItem(@RequestBody TodoItem todoItem) throws SQLException {
        todoItemService.updateTodoItem(todoItem);
    }

    @DeleteMapping("/todoItem/{id}")
    public void deleteTodoItem(@PathVariable("id") Long id) throws SQLException {
        todoItemService.deleteTodoItem(id);
    }

}