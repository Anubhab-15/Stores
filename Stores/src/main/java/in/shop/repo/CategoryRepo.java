package in.shop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import in.shop.entity.UserEntity;

public interface CategoryRepo extends JpaRepository<UserEntity, Long>{

	 boolean existsByName(String name);
	 
	 Optional<UserEntity> findByCategoryId(String categoryId );
}
