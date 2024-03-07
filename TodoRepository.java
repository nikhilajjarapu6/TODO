package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.dto.Todo;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
//	 List<Todo> findAllByUserId(int userId);
    @Query("SELECT t FROM Todo t WHERE t.user.id = :userId")
    List<Todo> findAllByUserId(@Param("userId") int userId);

}
