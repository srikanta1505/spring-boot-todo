package practice.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import practice.todo.entity.Todo;
import practice.todo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todorepositoty;
	
	public TodoServiceImpl(TodoRepository todorepo) {
		this.todorepositoty=todorepo;
	}

	@Override
	public Todo createTodo(Todo todo) {
		return todorepositoty.save(todo);
	}

	@Override
	public List<Todo> getAllTodos() {
		return todorepositoty.findAll();
	}

	@Override
	public Todo getTodoById(Long id) {
		return todorepositoty.findById(id).orElseThrow(()-> new RuntimeException("No Data Found with ID : "+id));
	}

}
