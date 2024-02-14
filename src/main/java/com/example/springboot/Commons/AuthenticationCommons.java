package com.example.springboot.Commons;

import com.example.springboot.Dto.UserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public UserDto validateToken(String token){
        ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.postForEntity("http:localhost:8181:/validate/{token}", null,
                UserDto.class);
        if(!userDtoResponseEntity.hasBody()){
            return null;
        }
        return userDtoResponseEntity.getBody();
    }
}
