package in.shop.service;

import java.util.List;

import in.shop.binding.CategoryRequest;
import in.shop.binding.CategoryResponse;

public interface CategoryService {

	CategoryResponse add(CategoryRequest request);
	
	List<CategoryResponse> read();
	
	void delete(String categoryId);
	
}
