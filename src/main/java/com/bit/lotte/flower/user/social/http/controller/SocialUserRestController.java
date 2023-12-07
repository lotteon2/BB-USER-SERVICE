package com.bit.lotte.flower.user.social.http.controller;


import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.social.dto.UserPhoneNumberDto;
import com.bit.lotte.flower.user.social.dto.command.UpdateUserInfoCommand;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.dto.response.UserMypageResponse;
import com.bit.lotte.flower.user.social.http.message.GetUserCouponCntRequest;
import com.bit.lotte.flower.user.social.http.message.GetUserLikesCntRequest;
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
  private final GetUserCouponCntRequest getUserCouponCntRequest;
  private final GetUserInfoService getUserInfoService;
  private final SocialUserLoginManager socialUserLoginManager;
  private final SocialUpdateUserService socialUserService;

  @PostMapping("/social")
  public ResponseEntity<UserLoginDataResponse> userLogin(
      @RequestBody UserLoginCommand userLoginCommand) {
    UserLoginDataResponse response = socialUserLoginManager.process(userLoginCommand);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/social/phone-number")
  public CommonResponse<String> userPhoneNumberUpdate(
      @RequestBody UserPhoneNumberDto phoneNumberDto, @RequestHeader Long userId) {
    socialUserService.updatePhoneNumber(userId, phoneNumberDto.getPhoneNumber());
    return CommonResponse.success("성공");
  }

  @GetMapping("/social")
  public CommonResponse<UserMypageResponse<UserDataDto>> getUserData(@RequestHeader Long userId) {
    UserDataDto userDataDto = getUserInfoService.getUserdata(userId);
    Long userLikesCnt = getUserLikesCntRequest.request(userId);
    Long userCouponCnt = getUserCouponCntRequest.request(userId);
    return CommonResponse.success(getUserMypageResponse(userDataDto, userLikesCnt, userCouponCnt));
  }

  @PutMapping("/social")
  public CommonResponse<String> updateUserData(@RequestHeader Long userId,
      @Valid @RequestBody UpdateUserInfoCommand command) {
    socialUserService.updateUserInfo(userId, command.getNickname(), command.getEmail(),
        command.getPhoneNumber());
    return CommonResponse.success("업데이트 성공");

  }

  private UserMypageResponse<UserDataDto> getUserMypageResponse(UserDataDto userDataDto, Long likesCnt,
      Long couponCnt) {
    return UserMypageResponse.builder().data(userDataDto).couponCnt(couponCnt)
        .likesCnt(likesCnt).build();
  }


}
