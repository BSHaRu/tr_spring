package kr.co.sboard.service;

import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.entity.UserEntity;
import kr.co.sboard.mapper.UserMapStruct;
import kr.co.sboard.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.repository.TermsRepository;

@Service
@Log4j2
public class UserSevice {

	@Autowired
	private TermsRepository tr;

	@Autowired
	private UserRepository ur;

	@Autowired
	private UserMapStruct ums;

	@Autowired
	private PasswordEncoder pe;

	public TermsEntity findByTerms() {
		return tr.findById(1).get();
	}

	public void save(UserDTO dto){
		// db에 pw 암호화 처리 후 저장
		dto.setPass(pe.encode(dto.getPass()));
		// DTO -> Entity
		UserEntity userEntity = ums.dtoToEntity(dto);
		ur.save(userEntity);
	}

	public UserDTO findByUid(String uid) {
		UserDTO dto = ums.userToDTO(ur.findById(uid).get());
		return dto;
	}


}