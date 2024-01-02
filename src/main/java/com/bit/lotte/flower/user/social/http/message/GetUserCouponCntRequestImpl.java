package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.http.feign.UserUsableCouponCntFeignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetUserCouponCntRequestImpl implements
    GetUserCouponCntRequest<UserId> {

  private final UserUsableCouponCntFeignRequest feignRequest;
  @Override
  public Integer request(UserId userId) {
    return feignRequest.getUsableUserCouponCnt(userId.getValue()).getData();
  }
}
