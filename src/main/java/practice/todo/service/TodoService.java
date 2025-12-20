package practice.todo.service;

import java.util.List;

import practice.todo.entity.Todo;

public interface TodoService {
	
	Todo createTodo(Todo todo);
	
	List<Todo> getAllTodos();
	
	Todo getTodoById(Long id);
}
