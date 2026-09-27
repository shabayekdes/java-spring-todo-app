package com.shabayekdes.todo.repository;

import java.util.List;

import com.shabayekdes.todo.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByOrderByCompletedAscIdDesc();
}
