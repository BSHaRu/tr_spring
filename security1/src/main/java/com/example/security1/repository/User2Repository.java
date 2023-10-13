package com.example.security1.repository;

import com.example.security1.entity.User2Entity;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 필요가 없음
// -> JpaRepository를 상속했기 때문에 어노테이션 생략이 가능함
public interface User2Repository extends JpaRepository<User2Entity, Integer> {

    // JPA Query Methods를 검색하면 자세한 문법 설명 볼 수 있음
    public User2Entity findByUid(String uid);

}
