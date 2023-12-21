package com.bit.lotte.flower.user.social.http.controller;


import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.social.dto.UserPhoneNumberDto;
import com.bit.lotte.flower.user.social.dto.command.UpdateUserInfoCommand;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.dto.response.UserMypageResponse;
import com.bit.lotte.flower.user.social.http.message.GetUserCouponCntRequest;
import com.bit.lotte.flower.user.social.http.message.GetUserLikesCntRequest;
import com.bit.lotte.flower.user.social.service.GetUserInfoServiceImpl;
import com.bit.lotte.flower.user.social.service.MapAuthIdToUserIdService;
import com.bit.lotte.flower.user.social.service.SocialUpdateUserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserRestController {

  private final MapAuthIdToUserIdService<AuthId> mapAuthIdToUserIdService;
  private final GetUserLikesCntRequest getUserLikesCntRequest;
  private final GetUserCouponCntRequest getUserCouponCntRequest;
  private final GetUserInfoServiceImpl getUserInfoServiceImpl;
  private final SocialUpdateUserService socialUserService;



  @GetMapping("/social")
  public CommonResponse<UserMypageResponse<UserDataDto>> getUserData(@RequestHeader Long userId) {
    Long oauthIdToUserId = mapAuthIdToUserIdService.convert(new AuthId(userId)).getValue();
    UserDataDto userDataDto = getUserInfoServiceImpl.getUserdata(oauthIdToUserId);
    Long userLikesCnt = getUserLikesCntRequest.request(oauthIdToUserId);
    Integer userCouponCnt = getUserCouponCntRequest.request(oauthIdToUserId);
    return CommonResponse.success(getUserMypageResponse(userDataDto, userLikesCnt, userCouponCnt));
  }

  @PutMapping("/social")
  public CommonResponse<String> updateUserData(@RequestHeader Long userId,
      @Valid @RequestBody UpdateUserInfoCommand command) {
    Long oauthIdToUserId = mapAuthIdToUserIdService.convert(new AuthId(userId)).getValue();
    socialUserService.updateUserInfo(oauthIdToUserId, command.getNickname(), command.getEmail(),
        command.getPhoneNumber());
    return CommonResponse.success("업데이트 성공");
  }

  private UserMypageResponse<UserDataDto> getUserMypageResponse(UserDataDto userDataDto, Long likesCnt,
      Integer couponCnt) {
    return UserMypageResponse.builder().data(userDataDto).couponCnt(couponCnt)
        .likesCnt(likesCnt).build();
  }


}
