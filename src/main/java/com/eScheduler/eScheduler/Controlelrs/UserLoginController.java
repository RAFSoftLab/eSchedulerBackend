package com.eScheduler.eScheduler.Controlelrs;

import com.eScheduler.eScheduler.model.UserLogin;
import com.eScheduler.eScheduler.responses.ResponseDTO;
import com.eScheduler.eScheduler.services.UserLoginService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users/information")
public class UserLoginController {

    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<UserLogin>> getUserByEmail(@RequestBody UserLogin credentials){
        try{
            UserLogin userLogin = userLoginService.getUserByCredentials(credentials.getEmail(), credentials.getPassword());
            return ResponseEntity.ok(new ResponseDTO<>("User found", true, userLogin));
        }catch (IllegalArgumentException e){
            return ResponseEntity.ok(new ResponseDTO<>("Password or Email is incorrect!", false, null));
        }
    }
}
