package itbrains.az.blog.repositories;

import itbrains.az.blog.dtos.userdtos.UserDashboardListDto;
import itbrains.az.blog.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
// findByFieldName
// List<UserEntity> findByFirstName
// findFirstByFieldName
// UserEntity findFirstByFirstName