package practice.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import practice.todo.dto.TodoRequestDto;
import practice.todo.dto.TodoResponseDto;
import practice.todo.dto.TodoUpdateDto;
import practice.todo.entity.Todo;
import practice.todo.service.TodoService;
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService=todoService;
	}
	
	// Using Entity
	/*
	  @PostMapping
		public Todo createTodo(@RequestBody Todo todo) {
		return todoService.createTodo(todo);
	}
	// Using DTO
	@PostMapping
	public TodoResponseDto createTodo(@RequestBody TodoRequestDto todo) {
		return todoService.createTodo(todo);
	}
	*/
	
	// Using ResponseEntity<>
	
	@PostMapping
	public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto reqTodo) {
		return ResponseEntity.ok(todoService.createTodo(reqTodo));
	}
	
	@GetMapping("/data")
	public List<Todo> getAllTodos(){
		return todoService.getAllTodos();
	}
	@GetMapping("/{id}")
	public Todo getTodoById(@PathVariable Long id) {
		return todoService.getTodoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TodoResponseDto> updateTodo(@RequestBody TodoUpdateDto updateReq, @PathVariable Long id){
		return ResponseEntity.ok(todoService.updateTodo(updateReq, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTodo (@PathVariable Long id){
       
		todoService.deleteTodo(id);
		return ResponseEntity.noContent().build();
	}
}
