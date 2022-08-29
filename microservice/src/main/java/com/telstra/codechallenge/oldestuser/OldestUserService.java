package com.telstra.codechallenge.oldestuser;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OldestUserService {

    private String githubSearchUrl="https://api.github.com/search/users?";
    private String oldestUserZeroFollowersQuery="q=followers:0&sort=joined&order=asc&page=1&per_page=";

    private RestTemplate restTemplate;

    public OldestUserService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public OldestUser getOldestUser(int accountNumber) {
        return restTemplate.getForObject(githubSearchUrl+oldestUserZeroFollowersQuery+accountNumber, OldestUser.class);
    }

}
