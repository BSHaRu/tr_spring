package kr.ch10.mapper;

import org.mapstruct.Mapper;

import kr.ch10.dto.User6DTO;
import kr.ch10.entity.User6Entity;

/* componentModel = "spring"
 *  - MapStruct가 Spring의 기능을 활용해서 Bean 등록해줌
 *  -> mapper를 통해 자동으로 관리하고 주입해줌
*/
@Mapper(componentModel = "spring")
public interface User6Mapper {
	
	public User6DTO toDTO(User6Entity user6entity);
	public User6Entity toEntity(User6DTO user6DTO);
}
