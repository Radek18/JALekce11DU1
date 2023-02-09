package com.engeto.Lekce11DU1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemService {

    static Connection connection;

    public TodoItemService() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoList", "todolist_crud", "asdf123");
    }

    public static List<TodoItem> getAllTodoItems() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM todoList.todoItem");
        List<TodoItem> resultList = new ArrayList<>();
        while (resultSet.next()) {
            TodoItem todoItem = extractTodoItemData(resultSet);
            resultList.add(todoItem);
        }
        return resultList;
    }

    public TodoItem getTodoItem(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM todoList.todoItem WHERE id = " + id);
        if (resultSet.next()) {
            return extractTodoItemData(resultSet);
        }
        return null;
    }

    public void saveTodoItem(TodoItem todoItem) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
            "INSERT INTO todoList.todoItem(text, isCompleted) VALUES ('" + todoItem.getItem() + "', " + todoItem.getDone() + ")");
    }

    public void setTodoItemAsDone(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE todoList.todoItem SET isCompleted = true WHERE id = " + id);
    }

    public void updateTodoItem(Long id, TodoItem todoItem) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE todoList.todoItem SET text = '" + todoItem.getItem() + "' WHERE id = " + id);
        statement.executeUpdate("UPDATE todoList.todoItem SET isCompleted = " + todoItem.getDone() + " WHERE id = " + id);
    }

    public void deleteTodoItem(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM todoList.todoItem WHERE id = " + id);
    }

    private static TodoItem extractTodoItemData(ResultSet resultSet) throws SQLException {
        return new TodoItem(resultSet.getLong("id"), resultSet.getString("text"), resultSet.getBoolean("isCompleted"));
    }

}