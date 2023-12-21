package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.social.http.feign.UserUsableCouponCntFeignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetUserCouponCntRequestImpl implements
    GetUserCouponCntRequest {

  private final UserUsableCouponCntFeignRequest feignRequest;
  @Override
  public Integer request(Long userId) {
    return feignRequest.getUsableUserCouponCnt(userId).getData();
  }
}
