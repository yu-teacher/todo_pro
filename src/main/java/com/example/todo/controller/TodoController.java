package com.example.todo.controller;

import com.example.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public String getAllTodos(@RequestParam(required = false) String filter, Model model) {
        if ("completed".equals(filter)) {
            model.addAttribute("todos", todoService.getCompletedTodos());
            model.addAttribute("activeFilter", "completed");
        } else if ("active".equals(filter)) {
            model.addAttribute("todos", todoService.getActiveTodos());
            model.addAttribute("activeFilter", "active");
        } else {
            model.addAttribute("todos", todoService.getAllTodos());
            model.addAttribute("activeFilter", "all");
        }

        model.addAttribute("todoDTO", new TodoDTO());
        return "todos";
    }

    @GetMapping("/{id}")
    public String getTodoById(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.getTodoById(id));
        return "todo-detail";
    }

    @PostMapping
    public String createTodo(@Valid @ModelAttribute("todoDTO") TodoDTO todoDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("todos", todoService.getAllTodos());
            return "todos";
        }

        todoService.createTodo(todoDTO);
        redirectAttributes.addFlashAttribute("successMessage", "할일이 성공적으로 추가되었습니다.");
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("todoDTO", todoService.getTodoById(id));
        return "todo-form";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id,
                             @Valid @ModelAttribute("todoDTO") TodoDTO todoDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "todo-form";
        }

        todoService.updateTodo(id, todoDTO);
        redirectAttributes.addFlashAttribute("successMessage", "할일이 성공적으로 수정되었습니다.");
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.deleteTodo(id);
        redirectAttributes.addFlashAttribute("successMessage", "할일이 성공적으로 삭제되었습니다.");
        return "redirect:/todos";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTodoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        TodoDTO updatedTodo = todoService.toggleTodoStatus(id);
        String status = updatedTodo.isCompleted() ? "완료" : "미완료";
        redirectAttributes.addFlashAttribute("successMessage", "할일 상태가 " + status + "로 변경되었습니다.");
        return "redirect:/todos";
    }

    @GetMapping("/search")
    public String searchTodos(@RequestParam String keyword, Model model) {
        model.addAttribute("todos", todoService.searchTodos(keyword));
        model.addAttribute("todoDTO", new TodoDTO());
        model.addAttribute("keyword", keyword);
        return "todos";
    }
}
