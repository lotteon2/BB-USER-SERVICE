package com.bit.lotte.flower.user.store.http.message;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.http.feign.InitStoreManagerStatusPendingFeignRequest;
import com.bit.lotte.flower.user.store.http.feign.dto.UpdateStoreManagerPendingStausDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitStoreManagerAuthorizationPublisherImpl implements
    InitStoreManagerAuthorizationPublisher<UserId> {

  private final InitStoreManagerStatusPendingFeignRequest request;

  @Override
  public void publish(UserId storeManagerId) {
    request.publish(new UpdateStoreManagerPendingStausDto(storeManagerId.getValue()));
  }
}
