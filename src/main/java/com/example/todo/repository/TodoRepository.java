package com.example.todo.repository;

import com.example.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 완료된 할일 목록 찾기
    List<Todo> findByCompletedTrue();

    // 미완료된 할일 목록 찾기
    List<Todo> findByCompletedFalse();

    // 우선순위 기준으로 정렬하여 찾기
    List<Todo> findAllByOrderByPriorityDesc();

    // 제목에 특정 키워드가 포함된 할일 찾기
    List<Todo> findByTitleContainingIgnoreCase(String keyword);
}
