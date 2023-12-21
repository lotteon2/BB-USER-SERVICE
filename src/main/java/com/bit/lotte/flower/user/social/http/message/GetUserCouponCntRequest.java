package com.bit.lotte.flower.user.social.http.message;

import org.springframework.stereotype.Component;

@Component
public interface GetUserCouponCntRequest {
  public Integer request(Long userId);

}
