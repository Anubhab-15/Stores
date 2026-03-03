package in.shop.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.shop.binding.CategoryRequest;
import in.shop.binding.CategoryResponse;
import in.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoryController {

	        
    private final CategoryService categoryService;
	
	@PostMapping("/admin/categories")
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse addCategory(@RequestBody CategoryRequest request) {
		return categoryService.add(request);
		}	
   
	@GetMapping
	public List<CategoryResponse> fetchCategories(){
		return  categoryService.read();
    	 }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/admin/categories/{categoryId}")
	public void remove(@PathVariable String categoryId) {
		try {
			categoryService.delete(categoryId);
		}catch(Exception e) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
	}
	}
	
}
