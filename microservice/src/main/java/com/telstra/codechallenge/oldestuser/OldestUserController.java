package com.telstra.codechallenge.oldestuser;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class OldestUserController {

    private OldestUserService oldestUserService;

    public OldestUserController(OldestUserService oldestUserService){
        this.oldestUserService = oldestUserService;
    }

    @RequestMapping(path = "/user/{accountNumber}", method  = RequestMethod.GET)
    public List<OldestUser.User> userList(@PathVariable int accountNumber ){

        OldestUser response = oldestUserService.getOldestUser(accountNumber);

        return response.getItems();
    }

}
