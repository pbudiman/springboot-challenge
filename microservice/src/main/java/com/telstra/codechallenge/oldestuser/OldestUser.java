package com.telstra.codechallenge.oldestuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OldestUser {

    private Integer total_count;
    private Boolean incomplete_result;
    private List<User> items;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class User {
        private Integer id;
        private String login;
        private String html_url;
    }
}
