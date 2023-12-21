package com.bit.lotte.flower.user.social.http.feign;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.dto.feign.SoftDeleteSocialUserDto;
import com.bit.lotte.flower.user.social.service.SoftDeleteStrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserFeignController {

  private final SoftDeleteStrategyService softDeleteStrategyService;

  @DeleteMapping( "/client/users")
  CommonResponse<Boolean> delete(SoftDeleteSocialUserDto<UserId> deleteUserDto){
    softDeleteStrategyService.userWithdrawal(deleteUserDto.getSocialId());
    return CommonResponse.success(Boolean.TRUE);
  }

}
