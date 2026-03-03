package in.shop.repo;

import java.util.Optional;

import in.shop.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import in.shop.entity.UserEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{

	 boolean existsByName(String name);
	 
	 Optional<CategoryEntity> findByCategoryId(String categoryId );


}
