package com.example.todo.dto;

import com.example.todo.domain.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    private Long id;

    @NotBlank(message = "제목은 필수입니다")
    @Size(min = 1, max = 100, message = "제목은 1-100자 사이여야 합니다")
    private String title;

    @Size(max = 1000, message = "설명은 최대 1000자까지 입력 가능합니다")
    private String description;

    private boolean completed;

    private int priority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.completed = todo.isCompleted();
        this.priority = todo.getPriority();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }

    public Todo toEntity() {
        return Todo.builder()
                .id(id)
                .title(title)
                .description(description)
                .completed(completed)
                .priority(priority)
                .build();
    }

}
