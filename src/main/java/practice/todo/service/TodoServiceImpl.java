package practice.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import practice.todo.dto.TodoRequestDto;
import practice.todo.dto.TodoResponseDto;
import practice.todo.dto.TodoUpdateDto;
import practice.todo.entity.Todo;
import practice.todo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService{

	
	private final TodoRepository todoRepository;
	
	public TodoServiceImpl(TodoRepository todoRepo) {
		this.todoRepository=todoRepo;
	}

	/*@Override
	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo);
	}*/

	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Todo getTodoById(Long id) {
		return todoRepository.findById(id).orElseThrow(()-> new RuntimeException("No Data Found with ID : "+id));
	}

	@Override
	public TodoResponseDto createTodo(TodoRequestDto reqDto) {
		Todo todo= new Todo();
		todo.setTitle(reqDto.getTitle());
		todo.setDescription(reqDto.getDescription());
		todo.setStatus(reqDto.getStatus());
		
		Todo savedData = todoRepository.save(todo);
		
		TodoResponseDto response = new TodoResponseDto();
		response.setTitle(savedData.getTitle());
		response.setCompleted(savedData.getStatus());
		response.setDescription(savedData.getDescription());
		return response;
	}

	@Override
	public TodoResponseDto updateTodo(TodoUpdateDto reqDto, Long id) {
		
		Todo todo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("No Data Found with ID : "+id));
		if(reqDto.getTitle()!=null) {
			todo.setTitle(reqDto.getTitle());
		}
		if(reqDto.getDescription()!=null)
		{
			todo.setDescription(reqDto.getDescription());
		}
		if(reqDto.getStatus()!=null)
		{
			todo.setStatus(reqDto.getStatus());
		}
		
		Todo updatedTodo = todoRepository.save(todo);
		
		TodoResponseDto response = mapToResponse(updatedTodo);
		
		return response;
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("No Data Found with ID : "+id));
		todoRepository.delete(todo);
	}
	
	public TodoResponseDto mapToResponse(Todo todo)
	{
		TodoResponseDto res = new TodoResponseDto();
		res.setDescription(todo.getDescription());
		res.setCompleted(todo.getStatus());
		res.setTitle(todo.getTitle());
		
		return res;
	}

}
