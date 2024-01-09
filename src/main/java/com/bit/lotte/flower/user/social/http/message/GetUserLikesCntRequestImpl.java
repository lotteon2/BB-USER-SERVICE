package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.http.feign.UserLikesFeignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetUserLikesCntRequestImpl implements
    GetUserLikesCntRequest<AuthId> {

  private final UserLikesFeignRequest userLikesFeignRequest;
  @Override
  public Long request(AuthId userId) {
    return userLikesFeignRequest.getUserLikesCnt(userId.getValue()).getData();
  }
}
