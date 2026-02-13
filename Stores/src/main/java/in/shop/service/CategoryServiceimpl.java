package in.shop.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import in.shop.binding.CategoryRequest;
import in.shop.binding.CategoryResponse;
import in.shop.entity.CategoryEntity;
import in.shop.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceimpl implements CategoryService {

   
     private final CategoryRepo repo;
    
     
     
	@Override
	public CategoryResponse add(CategoryRequest request) {
		
		 if (repo.existsByName(request.getName())) {
	         throw new RuntimeException("Category already exists: " + request.getName());
	     }
		
		CategoryEntity newCategory = ConvertToEntity(request);
		newCategory = repo.save(newCategory);
		return ConverToResponse(newCategory);
	}

	private CategoryResponse ConverToResponse(CategoryEntity newCategory) {
		return	CategoryResponse.builder()
		                .categoryid(newCategory.getCategoryid())
		                .name(newCategory.getName())
		                .description(newCategory.getDescription())
		                .bgcolor(newCategory.getBgcolor())
		                .imgurl(newCategory.getImgurl())
		                .createdAt(newCategory.getCreatedAt())
		                .updatedAt(newCategory.getUpdatedAt())
		                .build();
		
	}

	private CategoryEntity ConvertToEntity(CategoryRequest request) {
		
		return CategoryEntity.builder()
		              .categoryid(UUID.randomUUID().toString())
		              .name(request.getName())
		              .description(request.getDescription())
		              .bgcolor(request.getBgcolor())
		              .build();
		              
		 
	}

	@Override
	public List<CategoryResponse> read() {
		           return  repo.findAll()
				               .stream()
				               .map(entity -> ConverToResponse(entity))
				               .collect(Collectors.toList());
	}

	@Override
	public void delete(String categoryId) {
		CategoryEntity existingCategory= repo.findByCategoryId(categoryId)
				                        .orElseThrow(()->new RuntimeException("Category not found "+categoryId));
				   repo.delete(existingCategory);
		
	}

}
