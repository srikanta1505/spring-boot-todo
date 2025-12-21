package practice.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoRequestDto {
	
	@NotBlank(message = "Title must not be Empty")
	@Size(min = 4, max= 60, message = "Length range should be 3-60")
	private String title;
	
	@Size(max = 20, message = "Description must not exceed 200 characters")
	private String description;
	
	private Boolean status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
