package practice.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import practice.todo.dto.TodoRequestDto;
import practice.todo.dto.TodoResponseDto;
import practice.todo.entity.Todo;
import practice.todo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todoRepositoty;
	
	public TodoServiceImpl(TodoRepository todoRepo) {
		this.todoRepositoty=todoRepo;
	}

	/*@Override
	public Todo createTodo(Todo todo) {
		return todorepositoty.save(todo);
	}*/

	@Override
	public List<Todo> getAllTodos() {
		return todoRepositoty.findAll();
	}

	@Override
	public Todo getTodoById(Long id) {
		return todoRepositoty.findById(id).orElseThrow(()-> new RuntimeException("No Data Found with ID : "+id));
	}

	@Override
	public TodoResponseDto createTodo(TodoRequestDto reqDto) {
		Todo todo= new Todo();
		todo.setTitle(reqDto.getTitle());
		todo.setDescription(reqDto.getDescription());
		todo.setStatus(reqDto.getStatus());
		
		Todo savedData = todoRepositoty.save(todo);
		
		TodoResponseDto response = new TodoResponseDto();
		response.setTitle(savedData.getTitle());
		response.setCompleted(savedData.getStatus());
		response.setDescription(savedData.getDescription());
		return response;
	}

}
