package com.bit.lotte.flower.user.social.http.controller;

import static bloomingblooms.response.CommonResponse.success;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.social.dto.response.UserDataResponse;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.service.SocialUserLoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserRestController {

  private final SocialUserLoginManager socialUserLoginManager;

  @PostMapping("/users/social")
  public CommonResponse<UserDataResponse> userLogin(@RequestBody UserLoginCommand userLoginCommand){
      UserDataResponse response = socialUserLoginManager.process(userLoginCommand);
      return CommonResponse.<UserDataResponse>success(response,"로그인 성공");
  }

}
