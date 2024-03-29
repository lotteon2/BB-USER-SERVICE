package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.common.valueobject.AuthId;
import org.springframework.stereotype.Component;

@Component
public interface GetUserLikesCntRequest<ID extends AuthId> {
  public Long request(ID userId);
}
