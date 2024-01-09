package com.bit.lotte.flower.user.store.http.message;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import org.springframework.stereotype.Component;

@Component
public interface InitStoreManagerAuthorizationPublisher<T extends UserId> {

  public void publish(T storeManagerId);

}
