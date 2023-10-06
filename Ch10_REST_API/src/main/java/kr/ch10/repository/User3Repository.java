package kr.ch10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ch10.entity.User3Entity;

@Repository
public interface User3Repository extends JpaRepository<User3Entity, String> {

}
