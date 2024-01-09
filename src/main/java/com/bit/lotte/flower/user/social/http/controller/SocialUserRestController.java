package com.bit.lotte.flower.user.social.http.controller;


import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.dto.command.UpdateUserInfoCommand;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.dto.response.UserMypageResponse;
import com.bit.lotte.flower.user.social.http.message.GetUserCouponCntRequest;
import com.bit.lotte.flower.user.social.http.message.GetUserLikesCntRequest;
import com.bit.lotte.flower.user.social.service.GetUserInfoService;
import com.bit.lotte.flower.user.social.service.MapAuthIdToUserIdService;
import com.bit.lotte.flower.user.social.service.SocialUpdateUserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserRestController {

  private final MapAuthIdToUserIdService<AuthId> mapAuthIdToUserIdService;
  private final GetUserLikesCntRequest<UserId> getUserLikesCntRequest;
  private final GetUserCouponCntRequest<UserId> getUserCouponCntRequest;
  private final GetUserInfoService<UserId> getUserInfoService;
  private final SocialUpdateUserService socialUserService;



  @GetMapping("/social")
  public CommonResponse<UserMypageResponse> getUserData(@RequestHeader AuthId userId) {
    UserId oauthIdToUserId = mapAuthIdToUserIdService.convert(userId);
    UserDataDto userDataDto = getUserInfoService.getUserdata(oauthIdToUserId);
    Long userLikesCnt = getUserLikesCntRequest.request(oauthIdToUserId);
    Integer userCouponCnt = getUserCouponCntRequest.request(oauthIdToUserId);
    return CommonResponse.success(getUserMypageResponse(userDataDto, userLikesCnt, userCouponCnt));
  }

  @PutMapping("/social")
  public CommonResponse<String> updateUserData(@RequestHeader AuthId userId,
      @Valid @RequestBody UpdateUserInfoCommand command) {
    Long oauthIdToUserId = mapAuthIdToUserIdService.convert(userId).getValue();
    socialUserService.updateUserInfo(oauthIdToUserId, command.getNickname(), command.getEmail(),
        command.getPhoneNumber());
    return CommonResponse.success("업데이트 성공");
  }


  @GetMapping("/social/phone-number")
  public CommonResponse<String> getUserPhoneNumber(@RequestHeader AuthId userId) {
    UserId oauthIdToUserId = mapAuthIdToUserIdService.convert(userId);

    return CommonResponse.success(getUserInfoService.getUserdata(oauthIdToUserId).getPhoneNumber());
  }

  private UserMypageResponse getUserMypageResponse(UserDataDto userDataDto,
      Long likesCnt,
      Integer couponCnt) {
    return UserMypageResponse.builder().email(userDataDto.getEmail())
        .nickname(userDataDto.getNickname()).phoneNumber(
            userDataDto.getPhoneNumber()).profileImage(userDataDto.getProfileImage())
        .couponCnt(couponCnt)
        .likesCnt(likesCnt).build();
  }

}
