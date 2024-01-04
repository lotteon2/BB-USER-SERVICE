package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import org.springframework.stereotype.Component;

@Component
public interface GetUserLikesCntRequest<ID extends UserId> {
  public Long request(ID userId);
}
