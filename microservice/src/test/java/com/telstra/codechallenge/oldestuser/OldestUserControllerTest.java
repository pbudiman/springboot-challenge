package com.telstra.codechallenge.oldestuser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = OldestUserController.class)
public class OldestUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OldestUserService oldestUserService;

    @InjectMocks
    private OldestUserController oldestUserController= new OldestUserController(oldestUserService);

//    @Before
//    public void setUp(){
//        mvc = MockMvcBuilders.standaloneSetup(oldestUserController).build();
//    }

    @Test
    public void getUserList_returnOldestUserList() throws Exception {

        OldestUser.User user1= new OldestUser.User(1,"test","test1");
        OldestUser.User user2= new OldestUser.User(2,"test2","test2");
        Integer total_count=2;
        Boolean incomplete_result=false;

        OldestUser oldestUsers =  new OldestUser(total_count,incomplete_result, Arrays.asList(user1,user2));

        when(oldestUserService.getOldestUser(2)).thenReturn(oldestUsers);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/2").accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();


//        MockHttpServletResponse response = mvc.perform(get("/user/2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        System.out.println(mvcResult.getResponse());

    }

}
