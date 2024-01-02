package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import org.springframework.stereotype.Component;

@Component
public interface GetUserCouponCntRequest<ID extends UserId> {
  public Integer request(ID userId);

}
