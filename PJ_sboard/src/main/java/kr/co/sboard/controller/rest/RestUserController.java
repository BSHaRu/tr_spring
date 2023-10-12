package kr.co.sboard.controller.rest;

import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/*")
@RestController
public class RestUserController {

    @Autowired
    private UserSevice us;

    @GetMapping("check/${uid}")
    public UserDTO login(@PathVariable("uid") String uid) {
        UserDTO dto = us.findByUid(uid);
        return dto;
    }


}