package com.bit.lotte.flower.user.social.http.message;

import org.springframework.stereotype.Component;

@Component
public interface GetUserCouponCntRequest {
  public Long request(Long userId);

}
