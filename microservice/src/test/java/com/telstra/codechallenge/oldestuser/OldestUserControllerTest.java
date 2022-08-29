package com.telstra.codechallenge.oldestuser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootTest
public class OldestUserControllerTest {

    private RestTemplate restTemplate= mock(RestTemplate.class);

    private MockMvc mockMvc;

    @MockBean
    private OldestUserService oldestUserService=new OldestUserService(restTemplate);

    @InjectMocks
    private OldestUserController oldestUserController= new OldestUserController(oldestUserService);

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(oldestUserController).build();
    }


    @Test
    public void getUserList_returnOldestUserList() throws Exception {

        OldestUser expectedUsersData = new OldestUser();
        OldestUser.User user1 = new OldestUser.User();

        user1.setId(1);
        user1.setLogin("user1");
        user1.setHtml_url("https://github.com/user1");

        expectedUsersData.setTotal_count(1);
        expectedUsersData.setIncomplete_result(false);
        expectedUsersData.setItems(Arrays.asList(user1));

        String user1JsonArray= "[{\"id\":1,\"login\":\"user1\",\"html_url\":\"https://github.com/user1\"}]";

        when(oldestUserService.getOldestUser(1)).thenReturn(expectedUsersData);

        MockHttpServletResponse mvcResponse = mockMvc.perform(MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        int resStatus = mvcResponse.getStatus();
        String resJson= mvcResponse.getContentAsString();

        Assert.assertEquals(200,resStatus);
        Assert.assertEquals(user1JsonArray,resJson);
    }
}
