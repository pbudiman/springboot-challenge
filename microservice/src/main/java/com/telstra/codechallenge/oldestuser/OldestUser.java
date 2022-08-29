package com.telstra.codechallenge.oldestuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OldestUser {

    private Long total_count;
    private Boolean incomplete_result;
    private List<User> items;

//    public OldestUser(long total_count, boolean incomplete_result, List<User> items){
//        this.total_count=total_count;
//        this.incomplete_result=incomplete_result;
//        this.items=items;
//    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class User {
        private Long id;
        private String login;
        private String html_url;

//        public User(long id, String login, String html_url){
//            this.id=id;
//            this.login=login;
//            this.html_url=html_url;
//        }
    }


    public Long getTotalCount(){
        return total_count;
    }
    public List<User> getUsers(){
        return items;
    }
}
