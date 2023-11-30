package com.bit.lotte.flower.user.social.http.controller;

import com.bit.lotte.flower.user.social.dto.response.UserLoginResponse;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.service.SocialUserIdentifierResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserRestController {

  private final SocialUserIdentifierResponse socialUserIdentifierManager;

  @PostMapping("/users/social")
  public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserLoginCommand userLoginCommand){

  }

}
