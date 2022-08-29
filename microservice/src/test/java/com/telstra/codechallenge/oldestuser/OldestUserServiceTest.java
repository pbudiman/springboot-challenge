package com.telstra.codechallenge.oldestuser;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OldestUserServiceTest {

    private RestTemplate restTemplate= mock(RestTemplate.class);

    @InjectMocks
    private OldestUserService oldestUserService = new OldestUserService(restTemplate);

    @Test
    public void retrieveOldestUserZeroFollowersList() throws Exception{
        OldestUser expectedUsersData = new OldestUser();
        OldestUser.User user1 = new OldestUser.User();

        user1.setId(1);
        user1.setLogin("user1");
        user1.setHtml_url("https://github.com/user1");

        expectedUsersData.setTotal_count(1);
        expectedUsersData.setIncomplete_result(false);
        expectedUsersData.setItems(Arrays.asList(user1));

        String searchUrl = "https://api.github.com/search/users?q=followers:0&sort=joined&order=asc&page=1&per_page=1";

        when(restTemplate.getForObject(searchUrl,OldestUser.class)).thenReturn(expectedUsersData);

        OldestUser returnedUserData = oldestUserService.getOldestUser(1);
        Assert.assertEquals(expectedUsersData,returnedUserData);
    }
}
