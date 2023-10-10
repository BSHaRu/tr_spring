package kr.co.sboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.repository.TermsRepository;

@Service
public class UserSevice {

	@Autowired
	private TermsRepository tr;
	
	public TermsEntity findByTerms() {
		return tr.findById(1).get();
	}
}
