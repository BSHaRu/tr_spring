package kr.co.sboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.sboard.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	
}
