package kr.co.sboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.sboard.entity.TermsEntity;

@Repository
public interface TermsRepository extends JpaRepository<TermsEntity, Integer> {

	
}
