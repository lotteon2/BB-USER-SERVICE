package com.bit.lotte.flower.user.social.http.controller;


import com.bit.lotte.flower.user.social.dto.response.UserDataResponse;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.service.SocialUserLoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserRestController {

  private final SocialUserLoginManager socialUserLoginManager;

  @PostMapping("/users/social")
  public ResponseEntity<UserDataResponse> userLogin(@RequestBody UserLoginCommand userLoginCommand){
      UserDataResponse response = socialUserLoginManager.process(userLoginCommand);
      return ResponseEntity.ok(response);
  }

}
