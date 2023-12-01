package com.bit.lotte.flower.user.store.http.controller;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserDataResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreRestController {

  private StoreManagerCreateService storeManagerCreateService;

  @PostMapping("/users/stores")
  public CommonResponse<StoreManagerDataResponse> userLogin(@RequestBody UserLoginCommand userLoginCommand){
      UserDataResponse response = socialUserLoginManager.process(userLoginCommand);
      return CommonResponse.<UserDataResponse>success(response,"로그인 성공");
  }


}
