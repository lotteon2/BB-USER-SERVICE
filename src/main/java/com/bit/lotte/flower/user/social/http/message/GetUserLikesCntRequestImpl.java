package com.bit.lotte.flower.user.social.http.message;

import com.bit.lotte.flower.user.social.http.feign.UserLikesFeignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetUserLikesCntRequestImpl implements
    GetUserLikesCntRequest {

  private final UserLikesFeignRequest userLikesFeignRequest;
  @Override
  public Long request(Long userId) {
    return userLikesFeignRequest.getUserLikesCnt(userId).getData();
  }
}
