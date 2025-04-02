package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.dto.TodoDTO;
import com.example.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> getCompletedTodos() {
        List<Todo> todos = todoRepository.findByCompletedTrue();
        return todos.stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> getActiveTodos() {
        List<Todo> todos = todoRepository.findByCompletedFalse();
        return todos.stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id: " + id));
        return new TodoDTO(todo);
    }

    @Override
    @Transactional
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = todoDTO.toEntity();
        Todo savedTodo = todoRepository.save(todo);
        return new TodoDTO(savedTodo);
    }

    @Override
    @Transactional
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id: " + id));

        existingTodo.updateTodo(todoDTO);

        Todo updatedTodo = todoRepository.save(existingTodo);
        return new TodoDTO(updatedTodo);
    }

    @Override
    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TodoDTO toggleTodoStatus(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id: " + id));

        todo.setCompleted(!todo.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return new TodoDTO(updatedTodo);
    }

    @Override
    public List<TodoDTO> searchTodos(String keyword) {
        List<Todo> todos = todoRepository.findByTitleContainingIgnoreCase(keyword);
        return todos.stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }
}