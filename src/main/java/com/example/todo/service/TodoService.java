package com.example.todo.service;

import com.example.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    List<TodoDTO> getAllTodos();

    List<TodoDTO> getCompletedTodos();

    List<TodoDTO> getActiveTodos();

    TodoDTO getTodoById(Long id);

    TodoDTO createTodo(TodoDTO todoDTO);

    TodoDTO updateTodo(Long id, TodoDTO todoDTO);

    void deleteTodo(Long id);

    TodoDTO toggleTodoStatus(Long id);

    List<TodoDTO> searchTodos(String keyword);
}
