package com.bit.lotte.flower.user.store.http.message;

import com.bit.lotte.flower.user.common.valueobject.BaseId;
import org.springframework.stereotype.Component;

@Component
public interface InitStoreManagerAuthorizationPublisher<T extends BaseId> {

  public void publish(T storeManagerId);

}
