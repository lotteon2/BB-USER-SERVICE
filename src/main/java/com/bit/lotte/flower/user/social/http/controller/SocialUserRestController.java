package com.bit.lotte.flower.user.social.http.controller;


import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.social.dto.UserPhoneNumberDto;
import com.bit.lotte.flower.user.social.dto.command.UpdateUserInfoCommand;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.dto.response.UserMyPageDataResponse;
import com.bit.lotte.flower.user.social.http.feign.GetUserLikesCntRequest;
import com.bit.lotte.flower.user.social.service.GetUserInfoService;
import com.bit.lotte.flower.user.social.service.SocialUserLoginManager;
import com.bit.lotte.flower.user.social.service.SocialUpdateUserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserRestController {

  private final GetUserLikesCntRequest getUserLikesCntRequest;
  private final GetUserCounponCntRequest getUserLikesCntRequest;
  private final GetUserInfoService getUserInfoService;
  private final SocialUserLoginManager socialUserLoginManager;
  private final SocialUpdateUserService socialUserService;

  @PostMapping("/users/social")
  public ResponseEntity<UserLoginDataResponse> userLogin(
      @RequestBody UserLoginCommand userLoginCommand) {
    UserLoginDataResponse response = socialUserLoginManager.process(userLoginCommand);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/api/users/phone-number")
  public CommonResponse<String> userPhoneNumberUpdate(
      @RequestBody UserPhoneNumberDto phoneNumberDto, @RequestHeader Long userId) {
    socialUserService.updatePhoneNumber(phoneNumberDto.getPhoneNumber());
    return CommonResponse.success("성공");
  }

  @GetMapping("/api/users")
  public CommonResponse<UserMyPageDataResponse> getUserData(@RequestHeader Long userId) {

    return CommonResponse.success(getUserInfoService.getUserdata(userId));
  }

  @PutMapping("/api/users")
  public CommonResponse<String> updateUserData(
      @Valid @RequestBody UpdateUserInfoCommand command) {
    socialUserService.updateUserInfo(command.getNickname(), command.getEmail(),
        command.getPhoneNumber());
    return CommonResponse.success("업데이트 성공");

  }


}
