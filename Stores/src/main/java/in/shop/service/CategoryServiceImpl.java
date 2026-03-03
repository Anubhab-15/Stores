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
public class CategoryServiceImpl implements CategoryService {

   
     private final CategoryRepo repo;
    
     
     
	@Override
	public CategoryResponse add(CategoryRequest request) {
		
		 if (repo.existsByName(request.getName())) {
	         throw new RuntimeException("Category already exists: " + request.getName());
	     }
		
		
		CategoryEntity newCategory = convertToEntity(request);
		newCategory = repo.save(newCategory);
		return convertToResponse(newCategory);
	}

	private CategoryResponse convertToResponse(CategoryEntity entity) {
		return CategoryResponse.builder()
				.categoryid(entity.getCategoryId())
				.name(entity.getName())
				.description(entity.getDescription())
				.bgcolor(entity.getBgcolor())
				.imgurl(entity.getImgurl())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

	private CategoryEntity convertToEntity(CategoryRequest request) {
		
		return CategoryEntity.builder()
		              .categoryId(UUID.randomUUID().toString())
		              .name(request.getName())
		              .description(request.getDescription())
		              .bgcolor(request.getBgcolor())
		              .imgurl(request.getImgurl())
		              .build();
		              
		 
	}

	@Override
	public List<CategoryResponse> read() {
		           return  repo.findAll()
				               .stream()
				               .map(entity -> convertToResponse(entity))
				               .collect(Collectors.toList());
	}

	@Override
	public void delete(String categoryId) {
		CategoryEntity entity = repo.findByCategoryId(categoryId)
				.orElseThrow(() ->
						new IllegalArgumentException(
								"Category not found: " + categoryId
						)
				);

		repo.delete(entity);
		
	}

}
