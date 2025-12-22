package practice.todo.service;

import java.util.List;

import practice.todo.dto.TodoRequestDto;
import practice.todo.dto.TodoResponseDto;
import practice.todo.dto.TodoUpdateDto;
import practice.todo.entity.Todo;

public interface TodoService {
	
	//Todo createTodo(Todo todo);
	
	// DTO implementation
	TodoResponseDto createTodo(TodoRequestDto reqDto);
	
	List<Todo> getAllTodos();
	
	Todo getTodoById(Long id);
	
	TodoResponseDto updateTodo(TodoUpdateDto reqDto, Long id);
	
	void deleteTodo(Long id);
	
}
