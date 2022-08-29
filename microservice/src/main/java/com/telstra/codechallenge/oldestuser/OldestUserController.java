package com.telstra.codechallenge.oldestuser;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class OldestUserController {

    private final OldestUserService oldestUserService;

    public OldestUserController(OldestUserService oldestUserService){
        this.oldestUserService = oldestUserService;
    }
//can do unit testing to see account number is same or less or  https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/

    @RequestMapping(path = "/user/{accountNumber}", method  = RequestMethod.GET)
    public List<OldestUser.User> userList(@PathVariable int accountNumber ){

        OldestUser response = oldestUserService.getOldestUser(accountNumber);
        System.out.println(response);
        return response.getItems();
    }

}
