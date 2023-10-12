package kr.co.sboard.mapper;

import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    public UserDTO userToDTO(UserEntity userEntity);
    public UserEntity dtoToEntity(UserDTO userDTO);
}