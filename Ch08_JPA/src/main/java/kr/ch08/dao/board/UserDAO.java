package kr.ch08.dao.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ch08.entity.board.UserEntity;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, String> {

}
