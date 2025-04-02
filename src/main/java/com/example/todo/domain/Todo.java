package com.example.todo.domain;

import com.example.todo.dto.TodoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Setter
    @Builder.Default
    private boolean completed = false;

    @Builder.Default
    private int priority = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateTodo(TodoDTO todoDTO){
        this.title = todoDTO.getTitle();
        this.description = todoDTO.getDescription();
        this.completed = todoDTO.isCompleted();
        this.priority = todoDTO.getPriority();
    }

}
