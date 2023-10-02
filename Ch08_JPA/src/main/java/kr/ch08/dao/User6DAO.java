package kr.ch08.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ch08.entity.User6Entity;

/* JpaRepository<Entity, pk값의 자료형>
 	- Entity : @Entity와 @Table을 통해 mapping해서 Entity로 만들어줌
 	- pk값 자료형 : @id를 통해 pk값을 지정해주고 해당 자료형을 써주면 됨
*/ 	
@Repository
public interface User6DAO extends JpaRepository<User6Entity, String> {

	// JPA 쿼리 메서드(메서드 명에 규칙이 있음)
	// find : select
	// User6Entity : table
	// By : where
	// Uid : uid 
	public User6Entity findUser6EntityByUid(String uid);
	public List<User6Entity> findUser6EntityByName(String name);
	public List<User6Entity> findUser6EntityByNameNot(String name);
	
	public User6Entity findUser6EntityByUidAndName(String uid, String name);
	public List<User6Entity> findUser6EntityByUidOrName(String uid, String name);
	
	public List<User6Entity> findUser6EntityByAgeGreaterThan(int age);	
	public List<User6Entity> findUser6EntityByAgeGreaterThanEqual(int age);	
	public List<User6Entity> findUser6EntityByAgeLessThan(int age);	
	public List<User6Entity> findUser6EntityByAgeLessThanEqual(int age);	
	public List<User6Entity> findUser6EntityByAgeBetween(int low, int high);
	
	public List<User6Entity> findUser6EntityByNameLike(String name);
	public List<User6Entity> findUser6EntityByNameContains(String name);
	public List<User6Entity> findUser6EntityByNameStartsWith(String name);
	public List<User6Entity> findUser6EntityByNameEndsWith(String name);
	
	public List<User6Entity> findUser6EntityByOrderByName();
	public List<User6Entity> findUser6EntityByOrderByAgeAsc();
	public List<User6Entity> findUser6EntityByOrderByAgeDesc();
	public List<User6Entity> findUser6EntityByAgeGreaterThanOrderByAgeDesc(int age);
	
	public int countUser6EntityByUid(String uid);
	public int countUser6EntityByName(String name);
	
	
	// JPQL(이건 메서드 명 규칙 없음)
	// -> *는 못쓰고 테이블에 대한 별칭(AS)는 무조건 달아줘서 써야함
	@Query("select u6 from User6Entity as u6 where u6.age < 30")
	public List<User6Entity> selectUser6Age30();

	// 쿼리 파라미터가 붙으면 넘버값을 붙여줘야함
	@Query("select u6 from User6Entity as u6 where u6.name = ?1")
	public List<User6Entity> selectUser6ByName(String name);

	// 파라미터로 바인딩 시킬 때는 어노테이션을 사용하면서 아래처럼 해주면 됨
	@Query("select u6 from User6Entity as u6 where u6.name = :name")
	public List<User6Entity> selectUser6ByNameParam(@Param("name") String name);

	// 그냥 별칭만 붙이면 *이 되는데 이렇게 필요한 것만 뽑아 낼 수도 있음
	// -> 타입이 다 다를 수 있기 때문에 List<Object[]>로 받아야됨
	// => pk값(Uid)로 조회해서 1개나오지 다른 걸로 하면 여러개 나올 수 있어서 List인거임
	@Query("select u6.uid, u6.name "
			+ " from User6Entity as u6 where u6.uid = :uid")
	public List<Object[]> selectUser6ByUid(@Param("uid") String uid);
	
}
