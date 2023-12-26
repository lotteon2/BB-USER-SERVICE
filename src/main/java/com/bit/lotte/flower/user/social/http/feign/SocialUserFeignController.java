package com.bit.lotte.flower.user.social.http.feign;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.service.GetUserInfoService;
import com.bit.lotte.flower.user.social.service.MapAuthIdToUserIdService;
import com.bit.lotte.flower.user.social.service.SocialUserLoginManager;
import com.bit.lotte.flower.user.social.service.SoftDeleteStrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialUserFeignController {

  private final MapAuthIdToUserIdService<AuthId> mapAuthIdToUserIdService;
  private final SoftDeleteStrategyService softDeleteStrategyService;
  private final GetUserInfoService getUserInfoService;
  private final SocialUserLoginManager socialUserLoginManager;


  @PostMapping("/client/social")
  public CommonResponse<UserLoginDataResponse> userLogin(
      @RequestBody UserLoginCommand userLoginCommand) {
    UserLoginDataResponse response = socialUserLoginManager.process(userLoginCommand);
    return CommonResponse.success(response);
  }


  @GetMapping("/client/users/{userId}/phone-number")
  CommonResponse<String> getUserPhoneNumber(@PathVariable Long userId) {
    return CommonResponse.success(getUserInfoService.getUserdata(userId).getPhoneNumber());
  }

  @PutMapping("/client/users/{userId}")
  CommonResponse<Boolean> delete(@PathVariable Long userId) {
    UserId convertedUserId = mapAuthIdToUserIdService.convert(new AuthId(userId));
    softDeleteStrategyService.userWithdrawal(convertedUserId);
    return CommonResponse.success(Boolean.TRUE);
  }

}
