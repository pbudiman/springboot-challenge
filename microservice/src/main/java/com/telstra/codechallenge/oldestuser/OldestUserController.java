package com.telstra.codechallenge.oldestuser;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class OldestUserController {

    private OldestUserService responseService;

    public OldestUserController(OldestUserService responseService){
        this.responseService = responseService;
    }
//can do unit testing to see account number is same or less or  https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/

    @RequestMapping(path = "/user/{accountNumber}", method  = RequestMethod.GET)
    public List<OldestUser.User> userList(@PathVariable int accountNumber ){

        OldestUser response = responseService.getOldestUser(accountNumber);

        return response.getUsers();
    }

}
