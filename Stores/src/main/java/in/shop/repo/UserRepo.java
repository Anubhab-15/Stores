package in.shop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shop.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity>findByEmail(String email);
   
   Optional<UserEntity>findByUserId(String userId);
}
