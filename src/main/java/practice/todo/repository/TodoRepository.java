package practice.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practice.todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	
}
