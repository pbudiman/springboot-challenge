package com.telstra.codechallenge.oldestuser;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class OldestUserServiceTest {
    // why @Mock doesnt work here?
    private RestTemplate restTemplate= mock(RestTemplate.class);

    @InjectMocks
    private OldestUserService oldestUserService = new OldestUserService(restTemplate);

    @Test
    public void retrieveOldestUserZeroFollowersList(){
        OldestUser.User user1= new OldestUser.User();
        OldestUser.User user2= new OldestUser.User();
        user1.setId(1);user1.setLogin("user1");user1.setHtml_url("user1");
        user2.setId(2);user2.setLogin("user2");user2.setHtml_url("user2");
        OldestUser oldestUsers =  new OldestUser();
        oldestUsers.setTotal_count(2);oldestUsers.setIncomplete_result(false);oldestUsers.setItems(Arrays.asList(user1,user2));

        Mockito.when(
                restTemplate.getForObject("https://api.github.com/search/users?q=followers:0&sort=joined&order=asc&page=1&per_page=2",OldestUser.class))
                .thenReturn(oldestUsers);

        OldestUser oldestUser_ = oldestUserService.getOldestUser(2);

        System.out.println("res");
        System.out.println(oldestUser_);
    }
}
