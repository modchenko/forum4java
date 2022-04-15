package com.chenko.forum4java.forum4java.controller;

import com.chenko.forum4java.forum4java.dto.AccessTokenDTO;
import com.chenko.forum4java.forum4java.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private  String clientid;

    @Value("${github.client.secret}")
    private  String clientsecret;
    @Value("${github.redirect.url}")
    private  String redirecturl;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code" )String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret("clientsecret");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("redirecturl");
        accessTokenDTO.setState(state);
        githubProvider.getAccessToken(accessTokenDTO);
        return "index";

    }


}
