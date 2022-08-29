package com.telstra.codechallenge.oldestuser;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class OldestUserControllerTest {



    @InjectMocks
    private OldestUserController oldestUserController;

    @Mock
    private OldestUserService oldestUserService;

    @Autowired
    private MockMvc mvc;

//    @BeforeEach
//    public void setUp(){
//        mvc = MockMvcBuilders.standaloneSetup(oldestUserController).build();
//    }

    @Test
    public void getUserList_returnOldestUserList() throws Exception {

        OldestUser.User user1= new OldestUser.User();
        OldestUser.User user2= new OldestUser.User();
        user1.setId(1);user1.setLogin("user1");user1.setHtml_url("user1");
        user2.setId(2);user2.setLogin("user2");user2.setHtml_url("user2");
        OldestUser oldestUsers =  new OldestUser();
        oldestUsers.setTotal_count(2);oldestUsers.setIncomplete_result(false);oldestUsers.setItems(Arrays.asList(user1,user2));

        oldestUserService = Mockito.mock(OldestUserService.class, Mockito.RETURNS_DEEP_STUBS);
        when(oldestUserService.getOldestUser(2)).thenReturn(oldestUsers);

        mvc.perform(get("/user/2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();



    }




}
